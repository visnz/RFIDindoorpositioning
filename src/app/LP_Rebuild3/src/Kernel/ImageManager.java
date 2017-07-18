package Kernel;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;

/**
 * 图像管理器，每个图像管理器包含文件输入流和一张表
 *
 * Created by zyvis on 2017/3/5.
 */
public class ImageManager {
    protected HashMap<String, BufferedImage> imageTable=new HashMap<>();
    protected FileInputStream fileInputStream;

    public ImageManager() {
    }

    /**
     * fetch the image from file system
     * and save it as key-image in the image table
     * each ImageManager has its own image table;
     * @param key           the key linked to the image, for fetching
     * @param FileString    the path of the image : src/data/actor.jpg
     * @throws IOException
     */
    public void importImage(String key,String FileString) throws IOException {
        String[] filter={"png","jpg"};
        for(String e:filter) {
            if(FileString.endsWith(e)){
                fileInputStream=new FileInputStream(FileString);
                BufferedImage tmp= ImageIO.read(fileInputStream);
                imageTable.put(key,tmp);
                //待修改
                fileInputStream.close();
                return;
            }
        }
    }
    public BufferedImage fecth(String Key){
        try{return imageTable.get(Key);}
        catch (NullPointerException e){
            return null;
        }
    }

}
