package imamJmartMR;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.*;

public class Jmart {

    public static List<Product> filterByCategory(List<Product> list, ProductCategory category) {
        List<Product> filtered = new ArrayList<>();
        for (Product search : list) {
            if (search.category.equals(category)) {
                filtered.add(search);
            }
        }
        return filtered;
    }

    public static List<Product> filterByPrice(List<Product> list, double minPrice, double maxPrice) {
        if (minPrice == 0.0) {
            return null;
        }
        if (maxPrice == 0.0) {
            return null;
        }
        return null;
    }

    public static void main(String[] args) {
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
