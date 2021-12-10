package com.imamJmartMR.controller;

import com.imamJmartMR.Coupon;
import com.imamJmartMR.JsonTable;
import com.imamJmartMR.Treasury;
import com.imamJmartMR.dbjson.JsonAutowired;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/coupon")
public class CouponController implements BasicGetController<Coupon> {

    public @JsonAutowired(value = Coupon.class, filepath = "E:\\Imam Azka\\Semester 3\\Java\\Jmart\\src\\main\\resources\\CouponList.json") static JsonTable<Coupon> couponTable;

    @Override
    public JsonTable<Coupon> getJsonTable () {
        if (couponTable == null || couponTable.isEmpty())
            return null;
        return couponTable;
    }

    @GetMapping("/{id}/canApply")
    boolean canApply (@PathVariable int id, @RequestParam double price, @RequestParam double discount) {
        if (couponTable == null || couponTable.isEmpty())
            return false;
        for (Coupon get : couponTable) {
            if (get.id == id)
                return get.canApply(new Treasury(price, discount));
        }
        return false;
    }

    @GetMapping("/getAvailable")
    List<Coupon> getAvailable (@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "1") int pageSize) {

        if (couponTable == null || couponTable.isEmpty())
            return null;

        List<Coupon> temp = new ArrayList<>();

        for (Coupon get : couponTable) {
            if (!get.isUsed())
                temp.add(get);
        }
        int index = (page - 1) * pageSize;
        return temp.subList(index, Math.min(index + pageSize, temp.size()));
    }

    @GetMapping("/{id}/isUsed")
    boolean isUsed (@PathVariable int id) {
        if (couponTable == null || couponTable.isEmpty())
            return false;
        for (Coupon get : couponTable) {
            if (get.id == id)
                return get.isUsed();
        }
        return false;
    }
}
