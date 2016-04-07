package services;

import org.primefaces.model.UploadedFile;

import java.io.*;

/**
 * Utility class that help us to get images
 */
public class ImagesUtil {

    private static final String PATH = "pics/%s.jpg";
    private static final String REAL_PATH = "/data/openu/images/items/%s.jpg";

    public static void saveImage(UploadedFile uploadedFile, Integer itemId) {



        BufferedInputStream bufferedInputStream = null;
        BufferedOutputStream bufferedOutputStream = null;
        try {
            File file = new File(String.format(PATH, itemId.toString()));
            if (file.exists() == false) {
                file.createNewFile();
            }
            bufferedInputStream = new BufferedInputStream(uploadedFile.getInputstream());
            FileOutputStream fos = new FileOutputStream(file);
            bufferedOutputStream = new BufferedOutputStream(fos);
            int x;
            while((x = bufferedInputStream.read())!= -1){
                bufferedOutputStream.write(x);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            try {
                bufferedOutputStream.flush();
                bufferedOutputStream.close();
                bufferedInputStream.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static String getImagePath(String itemId) {
        return String.format(PATH, itemId);
    }

    public static boolean isImageExists(Integer id) {
        if (id == null) {
            return false;
        }
        return isImageExists(id.toString());
    }

    public static boolean isImageExists(String id) {
        if (id == null) {
            return false;
        }

        try {
            String path = String.format(REAL_PATH, id);
            File file = new File(path);

            return file.exists();
        } catch (Exception e) {
            return false;
        }
    }
}
