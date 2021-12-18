package com.imamJmartMR.controller;

import com.imamJmartMR.*;
import com.imamJmartMR.dbjson.JsonAutowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.function.Function;

import static com.imamJmartMR.Invoice.Status.*;

/**
 * Controller for all incoming and outcome of a transaction.
 * Handle incoming HTTP request and send response back to the caller.
 * @author Imam Azka Ramadhan Aditia
 * @version 1.0
 */

@RestController
@RequestMapping("/payment")
public class PaymentController implements BasicGetController<Payment>{

    public static final long DELIVERED_LIMIT_MS = 1000000; //15 minutes
    public static final long ON_DELIVERY_LIMIT_MS = 10000000; //3 hours limit
    public static final long ON_PROGRESS_LIMIT_MS = 1000000000; //11 days limit
    public static final long WAITING_CONF_LIMIT_MS = 1000000000; //11 days limit
    public @JsonAutowired(value = Payment.class, filepath = "E:\\Imam Azka\\Semester 3\\Java\\Jmart\\src\\main\\resources\\PaymentList.json") static JsonTable<Payment> paymentTable;
    public static ObjectPoolThread<Payment> poolThread = new ObjectPoolThread<>("Payment Thread", PaymentController::timekeeper);
    static {
        poolThread.start();
    }

    @Override
    public JsonTable<Payment> getJsonTable () {
        return paymentTable;
    }

    /**
     *
     * @return list of order with waiting confirmation status.
     */
    @GetMapping("/getOrder")
    List<Payment> getOrder(@RequestParam String status) {
        if (paymentTable == null || paymentTable.isEmpty())
            return null;
        List<Payment> temp = new ArrayList<>();
        if (status.equals(String.valueOf(WAITING_CONFIRMATION))) {
            for (Payment get : paymentTable) {
                if (get.history.size() == 0)
                    get.history.get(0).status.equals(WAITING_CONFIRMATION);
                else if (get.history.get(get.history.size() - 1).status.equals(WAITING_CONFIRMATION))
                    temp.add(get);
            }
        }
        else if (status.equals(String.valueOf(ON_PROGRESS))) {
            for (Payment get : paymentTable) {
                if (get.history.size() == 0)
                    get.history.get(0).status.equals(ON_PROGRESS);
                else if (get.history.get(get.history.size() - 1).status.equals(ON_PROGRESS))
                    temp.add(get);
            }
        }
        return temp;
    }

    /**
     *
     * @param buyerId id of the buyer
     * @return all order details of the buyer
     */
    @GetMapping("/{buyerId}/myOrder")
    List<Payment> myOrder(@PathVariable int buyerId) {
        if (paymentTable == null || paymentTable.isEmpty())
            return null;
        List<Payment> temp = new ArrayList<>();
        for (Payment get : paymentTable) {
            if (get.buyerId == buyerId)
                temp.add(get);
        }
        return temp;
    }

    /**
     *
     * @param id id of the order
     * @return condition of the accepted order
     */
    @PostMapping("/{id}/accept")
    boolean accept (@PathVariable int id) {
        if (paymentTable == null)
            return false;
        int i = 0;
        for (Payment get : paymentTable) {
            if (get.id == id && get.history.get(get.history.size() - 1).status.equals(Invoice.Status.WAITING_CONFIRMATION)) {
                paymentTable.get(i).history.add(new Payment.Record(Invoice.Status.ON_PROGRESS, "On progress"));
                return true;
            }
            i++;
        }
        return false;
    }

    /**
     *
     * @param id id of the order
     * @return condition of the rejected order
     */
    @PostMapping("/{id}/cancel")
    boolean cancel (@PathVariable int id) {
        if (paymentTable == null)
            return false;
        int i = 0;
        for (Payment get : paymentTable) {
            if (get.id == id && get.history.get(get.history.size() - 1).status.equals(Invoice.Status.WAITING_CONFIRMATION)) {
                paymentTable.get(i).history.add(new Payment.Record(Invoice.Status.CANCELLED, "Cancelled"));
                return true;
            }
            i++;
        }
        return false;
    }

    /**
     *
     * @param buyerId buyerId
     * @param productId id of the product
     * @param productCount amounts of the product
     * @param shipmentAddress address of the buyer
     * @param shipmentPlan plan of shipping
     * @return new created order details
     */
    @PostMapping("/create")
    Payment create (@RequestParam int buyerId, @RequestParam int productId, @RequestParam int productCount, @RequestParam String shipmentAddress, @RequestParam byte shipmentPlan) {
        if (paymentTable == null)
            return null;
        for (Account get1 : AccountController.accountTable) {
            if (get1.id == buyerId) {
                for (Product get2 : ProductController.productTable) {
                    if (get2.id == productId) {
                        Shipment shipment = new Shipment(shipmentAddress, 0, shipmentPlan, null);
                        Payment add = new Payment(buyerId, productId, productCount, shipment);
                        add.history.add(new Payment.Record(Invoice.Status.WAITING_CONFIRMATION, "Waiting confirmation"));
                        double total = add.getTotalPay(get2);
                        if (get1.balance >= total) {
                            paymentTable.add(add);
                            poolThread.add(add);
                            return add;
                        }
                    }
                }
            }
        }
        return null;
    }

    /**
     *
     * @param id id of the order
     * @param receipt new receipt of the order
     * @return condition of the receipt submit
     */

    @PostMapping("/{id}/submit")
    boolean submit (@PathVariable int id, @RequestParam String receipt) {
        if (paymentTable == null)
            return false;
        if (receipt.isBlank())
            return false;
        int i = 0;
        for (Payment get : paymentTable) {
            if (get.id == id && get.history.get(get.history.size() - 1).status.equals(Invoice.Status.ON_PROGRESS)) {
                paymentTable.get(i).shipment.receipt = receipt;
                paymentTable.get(i).history.add(new Payment.Record(Invoice.Status.ON_DELIVERY, "On delivery"));
                poolThread.add(paymentTable.get(i));
                return true;
            }
            i++;
        }
        return false;
    }

    /**
     * Timekeeper for the order
     * @param payment details of the order
     * @return condition of the proccessed order
     */
    private static boolean timekeeper (Payment payment) {
        if (payment.history.isEmpty())
            return true;

        final Date currentDate = Calendar.getInstance().getTime();
        long elapsed = currentDate.getTime() - payment.history.get(payment.history.size() - 1).date.getTime();
        Function<Payment,Boolean> pass = new Function<Payment, Boolean>() {
            @Override
            public Boolean apply(Payment payment) {
                return null;
            }
        };

        switch (payment.history.get(payment.history.size() - 1).status) {
            case WAITING_CONFIRMATION:
                if (elapsed > WAITING_CONF_LIMIT_MS) {
                    payment.history.add(new Payment.Record(FAILED, "Failed"));
                }
                break;
            case ON_PROGRESS:
                if (elapsed > ON_PROGRESS_LIMIT_MS) {
                    payment.history.add(new Payment.Record(FAILED, "Failed"));
                }
                break;
            case ON_DELIVERY:
                if (elapsed > ON_DELIVERY_LIMIT_MS) {
                    payment.history.add(new Payment.Record(DELIVERED, "Delivered"));
                }
                break;
            case DELIVERED:
                if (elapsed > DELIVERED_LIMIT_MS) {
                    payment.history.add(new Payment.Record(FINISHED, "Finished"));
                }
                break;
            default:
                return true;
        }
        return true;
    }
}
