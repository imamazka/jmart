package com.imamJmartMR.controller;

import com.imamJmartMR.Algorithm;
import com.imamJmartMR.JsonTable;
import com.imamJmartMR.Serializable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public interface BasicGetController<T extends Serializable> {

    @GetMapping(value ="/{id}")
    @ResponseBody
    public default T getById (@RequestParam int id) {
        return null;
    }

    public abstract JsonTable<T> getJsonTable ();

    @GetMapping("/page")
    @ResponseBody
    public default List<T> getPage (int page, int pageSize) {
        return null;
    }
}
