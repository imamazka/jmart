package com.imamJmartMR.controller;

import com.imamJmartMR.JsonTable;
import com.imamJmartMR.Serializable;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Interface for controller.
 * Handle incoming HTTP request and send response back to the caller.
 * @author Imam Azka Ramadhan Aditia
 * @version 1.0
 */

@RestController
public interface BasicGetController<T extends Serializable> {

    public abstract JsonTable<T> getJsonTable ();

    @GetMapping("/{id}")
    public default T getById (@PathVariable int id) {
        int i = 0;
        while (!getJsonTable().isEmpty()) {
            T get = getJsonTable().get(i);
            if (get.id == id)
                return get;
            i++;
        }
        return null;
    }

    @GetMapping("/page")
    public default List<T> getPage (@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "1") int pageSize) {

        int fromIndex = (page - 1) * pageSize;
        return getJsonTable().subList(fromIndex, Math.min(fromIndex + pageSize, getJsonTable().size()));
    }
}
