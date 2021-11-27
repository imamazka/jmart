package com.imamJmartMR.controller;


import com.imamJmartMR.*;
import com.imamJmartMR.dbjson.JsonAutowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.function.Function;

@RestController
@RequestMapping("/payment")
public class PaymentController implements BasicGetController<Payment>{

    public static final long DELIVERED_LIMIT_MS = 1000;
    public static final long ON_DELIVERED_LIMIT_MS =  1000;
    public static final long ON_PROGRESS_LIMIT_MS = 1000;
    public static final long WAITING_CONF_LIMIT_MS = 1000;
    public @JsonAutowired(value = Payment.class, filepath = "\\imamJmartMR\\randomPaymentList.json") static JsonTable<Payment> paymentTable;
    public static ObjectPoolThread<Payment> poolThread = new ObjectPoolThread<Payment>(null);

    @Override
    @ResponseBody
    public JsonTable<Payment> getJsonTable () {
        return paymentTable;
    }

    @PostMapping("/{id}/accept")
    boolean accept (int id) {
        int i = 0;
        for (Payment get : paymentTable) {
            if (get.id == id && get.history.get(i).status == Invoice.Status.WAITING_CONFIRMATION) {
                get.history.add(new Payment.Record(Invoice.Status.ON_PROGRESS, "on progress"));
                return true;
            }
            i++;
        }
        return false;
    }

    @PostMapping("/{id}/cancel")
    boolean cancel (int id) {
        int i = 0;
        for (Payment get : paymentTable) {
            if (get.id == id && get.history.get(i).status == Invoice.Status.WAITING_CONFIRMATION) {
                get.history.add(new Payment.Record(Invoice.Status.CANCELLED, "cancelled"));
                return true;
            }
        }
        return false;
    }

    @PostMapping("/create")
    @ResponseBody
    Payment create (int buyerId, int productId, int productCount, String shipmentAddress, byte shipmentPlan) {
        for (Account get1 : AccountController.accountTable) {
            if (get1.id == buyerId) {
                for (Product get2 : ProductController.productTable) {
                    if (get2.id == productId) {
                        Shipment shipment = new Shipment(shipmentAddress, 0, shipmentPlan, null);
                        Payment add = new Payment(buyerId, productId, productCount, shipment);
                        double total = add.getTotalPay(get2);
                        if (get1.balance > total) {
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
    boolean submit (int id, String receipt) {
        int i = 0;
        if (receipt.isBlank())
            return false;
        for (Payment get : paymentTable) {
            if (get.id == id && get.history.get(i).status == Invoice.Status.ON_PROGRESS) {
                get.history.add(new Payment.Record(Invoice.Status.ON_DELIVERY, "on delivery"));
                return true;
            }

        }
        return false;
    }

    private static boolean timekeeper (Payment payment) {
        Function<Payment,Boolean> f = new Function<Payment, Boolean>() {
            @Override
            public Boolean apply(Payment payment) {
                return null;
            }
        };
        poolThread.start();
        return false;
    }
}
