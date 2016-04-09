package services;

import org.primefaces.model.UploadedFile;

import java.io.*;

/**
 * Utility class that help us to get images
 */
public class ImagesUtil {

    private static final String PATH = "pics/%s.jpg";
    private static final String REAL_PATH = System.getProperty("disk.images", "/data/openu/images/items/%s.jpg");

    /**
     * Save an image to disk.
     *
     * @param uploadedFile
     * @param itemId
     */
    public static void saveImage(UploadedFile uploadedFile, Integer itemId) {

        BufferedInputStream bufferedInputStream = null;
        BufferedOutputStream bufferedOutputStream = null;

        try {

            File file = new File(String.format(REAL_PATH, itemId.toString()));
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

    /**
     * Get the image logical path as resource
     * @param itemId
     * @return
     */
    public static String getImagePath(String itemId) {
        return String.format(PATH, itemId);
    }
}
