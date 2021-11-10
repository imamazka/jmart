package imamJmartMR;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.google.gson.*;

public class Jmart {

    public static List<Product> filterByAccountId (List<Product> list, int accountId, int page, int pageSize) {
        List<Product> filtered = new ArrayList<>();
        for (Product find : list) {
            if (find.id == accountId) {
                filtered.add(find);
            }
        }
        filtered = paginate(filtered, page, pageSize, null);
        return filtered;
    }

    public static List<Product> filterByCategory(List<Product> list, ProductCategory category) {
        List<Product> filtered = new ArrayList<>();
        for (Product search : list) {
            if (search.category.equals(category)) {
                filtered.add(search);
            }
        }
        return filtered;
    }

    public static List<Product> filterByName(List<Product> list, String search, int page, int pageSize) {
        List<Product> filtered = new ArrayList<>();
        for (Product find : list) {
            if (find.name.equals(search)) {
                filtered.add(find);
            }
        }
        filtered = paginate(filtered, page, pageSize, null);
        return filtered;
    }

    public static List<Product> filterByPrice(List<Product> list, double minPrice, double maxPrice) {

        List<Product> filtered = new ArrayList<>();

        if (minPrice == 0.0) {
            for (Product search : list) {
                if (search.price <= maxPrice) {
                    filtered.add(search);
                }
            }
            return filtered;
        }
        else if (maxPrice == 0.0) {
            for (Product search : list) {
                if (search.price >= minPrice) {
                    filtered.add(search);
                }
            }
            return filtered;
        }
        else {
            return null;
        }
    }

    public static void main(String[] args) {


    }

    private static List<Product> paginate (List<Product> list, int page, int pageSize, Predicate<Product> pred) {

        int i = 0;

        if (pred.predicate(list.get(i)) == true) {

            if(pageSize <= 0 || page <= 0) {
                throw new IllegalArgumentException("invalid page size: " + pageSize);
            }

            int index = (page - 1) * pageSize;
            return list.subList(index, Math.min(index + pageSize, list.size()));
        }

        return Collections.emptyList();
    }

    public static List<Product> read (String filepath) throws FileNotFoundException {

        Gson gson = new Gson();
        List<Product> temp = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(filepath));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
