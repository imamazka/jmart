package com.imamJmartMR.controller;

import com.imamJmartMR.Coupon;
import com.imamJmartMR.JsonTable;
import com.imamJmartMR.Treasury;
import com.imamJmartMR.dbjson.JsonAutowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/coupon")
public class CouponController implements BasicGetController<Coupon> {

    public @JsonAutowired(value = Coupon.class, filepath = "\\imamJmartMR\\randomCouponList.json") static JsonTable<Coupon> couponTable;

    @GetMapping("/{id}/canApply")
    boolean canApply (int id, double price, double discount) {
        for (Coupon get : couponTable) {
            if (get.id == id)
                return get.canApply(new Treasury(price, discount));
        }
        return false;
    }

    @GetMapping("/getAvailable")
    List<Coupon> getAvailable (int page, int pageSize) {

        List<Coupon> temp = new ArrayList<Coupon>();

        for (Coupon get : couponTable) {
            if (get.isUsed() == false)
                temp.add(get);
        }
        int index = (page - 1) * pageSize;
        return temp.subList(index, Math.min(index + pageSize, temp.size()));
    }

    @Override
    public JsonTable<Coupon> getJsonTable () {
        return couponTable;
    }

    @GetMapping("/{id}/isUsed")
    boolean isUsed (int id) {
        for (Coupon get : couponTable) {
            if (get.id == id)
                return get.isUsed();
        }
        return false;
    }
}
