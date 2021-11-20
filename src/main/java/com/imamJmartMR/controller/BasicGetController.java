package com.imamJmartMR.controller;

import com.imamJmartMR.JsonTable;
import com.imamJmartMR.Serializable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public interface BasicGetController<T extends Serializable> {

    @GetMapping("/{id}")
    public @ResponseBody default T getById (int id) {
        return null;
    }

    public abstract JsonTable<T> getJsonTable ();

    @GetMapping("/page")
    public @ResponseBody default List<T> getPage (int page, int pageSize) {
        return null;
    }
}
