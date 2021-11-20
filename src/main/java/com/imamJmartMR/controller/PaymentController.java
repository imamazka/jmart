package com.imamJmartMR.controller;


import com.imamJmartMR.JsonTable;
import com.imamJmartMR.ObjectPoolThread;
import com.imamJmartMR.Payment;
import com.imamJmartMR.dbjson.JsonAutowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
public class PaymentController implements BasicGetController<Payment>{

    public static final long DELIVERED_LIMIT_MS = 1000;
    public static final long ON_DELIVERED_LIMIT_MS =  2000;
    public static final long ON_PROGRESS_LIMIT_MS = 3000;
    public static final long WAITING_CONF_LIMIT_MS = 4000;
    public @JsonAutowired(value = Payment.class, filepath = "E:\\Imam Azka\\Semester 3\\Java\\Jmart\\src\\main\\java\\com\\imamJmartMR\\json.txt") static JsonTable<Payment> paymentTable;
    public static ObjectPoolThread<Payment> poolThread;


    @PostMapping("/{id}/accept")
    boolean accept (int id) {
        return false;
    }

    @PostMapping("/{id}/cancel")
    boolean cancel (int id) {
        return false;
    }

    @PostMapping("/create")
    Payment create (int buyerId, int productId, int productCount, String shipmentAddress, byte shipmentPlan) {
        return null;
    }

    @Override
    public JsonTable<Payment> getJsonTable () {
        return null;
    }

    @PostMapping("/{id}/submit")
    boolean submit (int id, String receipt) {
        return false;
    }

    private static boolean timekeeper (Payment payment) {
        return false;
    }
}
