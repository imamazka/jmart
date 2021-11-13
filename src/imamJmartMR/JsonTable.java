package imamJmartMR;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Vector;
import java.io.File;
import com.google.gson.*;

public class JsonTable <T extends Vector>{

    public static String filepath;
    private static final Gson gson = new Gson();

    public JsonTable (Class<T> clazz, String filepath) throws IOException{

        this.filepath = filepath;
        Class<T[]> array = (Class<T[]>) Array.newInstance(clazz, 0).getClass();
        T[] read = readJson(array, filepath);
    }

    public static<T> T readJson (Class<T> clazz, String filepath) throws FileNotFoundException {

        clazz = gson.fromJson(filepath, clazz.getClass());
        return null;
    }

    public void writeJson () {
        try {
            FileWriter writer = new FileWriter(filepath);
            gson.toJson(writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeJson (Object object, String filepath) {

        try {
            gson.toJson(object, new FileWriter(filepath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
