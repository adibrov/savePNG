package savePNG;
import java.util.Random;
//import java.awt.image.BufferedImage;
import java.awt.image.*;
import javax.imageio.*;
import java.lang.*;
import java.io.*;


public class savePNG {
    
    public static void savePNG(int[][] imageArray, String savePath, String imageFileName) {
        /* --- funtion to build and save a .png from an int array --- */
        
        
        // exception handling        
        if (imageArray == null) {
            throw new java.lang.NullPointerException("Array is null.");
        }
        
        int height = imageArray.length;
        
        if (height == 0) {
            throw new java.lang.IllegalArgumentException("Array is empty.");
        }
             
        int width = imageArray[0].length;
        
        if (width == 0) {
            throw new java.lang.IllegalArgumentException("first string of the input array is empty.");
        }
        
        // check
        System.out.println("height is: " + height + " width is : " + width);
        
        
        //function per se
        
        // create a 1d array for flattened data
        int[] data = new int[height*width];
        
        // fill it
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                data[i*width + j] = imageArray[i][j];
            }
        }
        
        // creating a buffered image
        BufferedImage im = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);
        // putting the array into the buffered image
        WritableRaster dataRaster = im.getRaster();
        
       
        
        dataRaster.setPixels(0, 0, width, height, data);
        
    //    im.setRGB(0, 0, width, height, data, 0, width);

        // creating the output file
        
        File out = new File(savePath+imageFileName);
        try {
            ImageIO.write(im, "png", out);
        } catch (IOException e) {
            System.out.println("IO exception during the writing process.");
        }
    }
    
    public static void main(String[] args) throws IOException {
        System.out.println("This example will save a byte/int array as a .png image:");
        
        Random randGen = new Random();
       
        //generate a random array
        
        int width = 1280;
        int height = 1024;
        int[][] arr = new int[height][width];
             
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                arr[i][j] = randGen.nextInt(256);
            }
        }
        
        String stdPath = "/Users/Dibrov/Documents/PhD/randomImages/";
        String stdName = "test.png";
        savePNG(arr, stdPath, stdName);
     
        
    }
}
