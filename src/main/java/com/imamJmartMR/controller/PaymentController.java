package com.imamJmartMR.controller;

import com.imamJmartMR.*;
import com.imamJmartMR.dbjson.JsonAutowired;
import org.springframework.web.bind.annotation.*;

import java.util.function.Function;

import static com.imamJmartMR.Invoice.Status.FAILED;

@RestController
@RequestMapping("/payment")
public class PaymentController implements BasicGetController<Payment>{

    public static final long DELIVERED_LIMIT_MS = 10000;
    public static final long ON_DELIVERY_LIMIT_MS =  10000;
    public static final long ON_PROGRESS_LIMIT_MS = 10000;
    public static final long WAITING_CONF_LIMIT_MS = 10000;
    public @JsonAutowired(value = Payment.class, filepath = "E:\\Imam Azka\\Semester 3\\Java\\Jmart\\src\\main\\resources\\PaymentList.json") static JsonTable<Payment> paymentTable;
    public static ObjectPoolThread<Payment> poolThread = new ObjectPoolThread<>(PaymentController::timekeeper);
    static {
        try {
            poolThread.start();
            paymentTable.forEach(payment -> poolThread.add(payment));
            while (poolThread.size() != 0);
            poolThread.exit();
            while (poolThread.isAlive());
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    @Override
    public JsonTable<Payment> getJsonTable () {
        return paymentTable;
    }

    @PostMapping("/{id}/accept")
    boolean accept (@PathVariable int id) {
        if (paymentTable == null || paymentTable.isEmpty())
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

    @PostMapping("/{id}/cancel")
    boolean cancel (@PathVariable int id) {
        if (paymentTable == null || paymentTable.isEmpty())
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

    @PostMapping("/create")
    Payment create (@RequestParam int buyerId, @RequestParam int productId, @RequestParam int productCount, @RequestParam String shipmentAddress, @RequestParam byte shipmentPlan) {
        if (paymentTable == null || paymentTable.isEmpty())
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
                            return add;
                        }
                    }
                }
            }
        }
        return null;
    }

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
                return true;
            }
            i++;
        }
        return false;
    }

    private static boolean timekeeper (Payment payment) {
        if (payment.history.isEmpty())
            return true;

        long start = System.currentTimeMillis();
        Function<Payment,Boolean> pass = new Function<Payment, Boolean>() {
            @Override
            public Boolean apply(Payment payment) {
                return null;
            }
        };
        long finish = System.currentTimeMillis();
        long elapsed = finish - start;

        switch (payment.history.get(payment.history.size() - 1).status) {
            case WAITING_CONFIRMATION:
                if (elapsed > WAITING_CONF_LIMIT_MS) {
                    payment.history.add(new Payment.Record(FAILED, "failed"));
                }
                break;
            case ON_PROGRESS:
                if (elapsed > ON_PROGRESS_LIMIT_MS) {
                    payment.history.add(new Payment.Record(FAILED, "failed"));
                }
                break;
            case ON_DELIVERY:
                if (elapsed > ON_DELIVERY_LIMIT_MS) {
                    payment.history.add(new Payment.Record(FAILED, "failed"));
                }
                break;
            case FINISHED:
                if (elapsed > DELIVERED_LIMIT_MS) {
                    payment.history.add(new Payment.Record(FAILED, "failed"));
                }
                break;
            default:
                return true;
        }
        return true;
    }
}
