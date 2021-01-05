/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imageprocessinghistogram;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

/**
 *
 * @author User
 */
public class FXMLImageHistogramController implements Initializable {

    @FXML
    private ImageView input_image_view;
    @FXML
    private ImageView output_image_view;
    private BufferedImage input_image_buffer;
    private BufferedImage output_image_buffer;
    private int[] histogram;
    @FXML
    private MenuItem menu_histogram_chart;
    @FXML
    private MenuBar menu_bar;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void doImportImage(ActionEvent event) throws FileNotFoundException, IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image File", "*.jpg"),
                new FileChooser.ExtensionFilter("Image File", "*.png")
        );
        File showOpenDialog = fileChooser.showOpenDialog(null);
        if (showOpenDialog != null) {
            input_image_buffer = ImageIO.read(showOpenDialog);
            input_image_view.setImage(new Image(new FileInputStream(showOpenDialog)));
        }
    }

    @FXML
    private void doHistogramChart(ActionEvent event) throws IOException {
        histogram = new int[256];
        int imageHeight = input_image_buffer.getHeight();
        int imageWidth = input_image_buffer.getWidth();
        
        WritableRaster raster = input_image_buffer.getRaster();
        for (int row = 0; row < imageHeight; row++) {
            for (int col = 0; col < imageWidth; col++) {
                int pixel = raster.getSample(col,row, 0);
                histogram[pixel]++;
            }
        }

        FXMLLoader fXMLLoader
                = new FXMLLoader(getClass().getResource("FXMLHistogramChart.fxml"));
        Parent parent = fXMLLoader.load();

        FXMLHistogramChartController chartController = (FXMLHistogramChartController) fXMLLoader.getController();
        Scene scene = new Scene(parent);
        Stage stage = (Stage) menu_bar.getScene().getWindow();
        stage.setScene(scene);

        chartController.makeChart(histogram);
    }

    @FXML
    private void doHistogramEqualization(ActionEvent event) {
        histogram = new int[256];
        int imageHeight = input_image_buffer.getHeight();
        int imageWidth = input_image_buffer.getWidth();
        int[] cdf = new int[256];
        int minCdf=1;
        int L;
        int totalImagePixel = 0;
        float totalMinusMinCdf;
        int[] rov = new int[256];
        int rovMatchCount = 0;
        int[] newPixelValue = new int[256]; // wikipedia tell it's h

        WritableRaster raster = input_image_buffer.getRaster();
        for (int row = 0; row < imageHeight; row++) {
            for (int col = 0; col < imageWidth; col++) {
                int pixel = raster.getSample(col, row, 0);
                histogram[pixel]++;
                //System.out.print(pixel+"|");
            }
            //System.out.println("");
        }

        System.out.println("Input Image Pixel Value Count.");
        for (int i = 0; i < histogram.length; i++) {
            System.out.println("pixel[" + i + "] = " + histogram[i]);
        }

        totalImagePixel = imageHeight * imageWidth;
        System.out.println("Sum of Pixel Count = " + totalImagePixel);
        
        L = histogram.length - 1;      
        
        
        //Calculating CDF
        for(int i = 0; i<histogram.length; i++){
            if(i == 0){
                cdf[i] = histogram[i];
            }
            else cdf[i] = cdf[i-1] + histogram[i];
                
        }
        
        //Calculating Min CDF
        for(int i = 0; i<cdf.length; i++){        
           if(cdf[i] != 0){
               minCdf = cdf[i];
               break;
           }               
        }
        
        totalMinusMinCdf = totalImagePixel - minCdf;
        
        
        System.out.println("Minimum value of cdf = " + minCdf);
        for(int i = 0; i<cdf.length; i++){
            System.out.println("cdf["+i+"]= " + cdf[i]);
        }       
        
        
        
        //Calculating ROV and newPixelValue
        for(int i = 0; i<histogram.length; i++){
            rov[i] = Math.round(((cdf[i] - minCdf)/totalMinusMinCdf) * L);
            
            if(i == 0 || rov[i-1] != rov[i] || (rov[i] == 0 && histogram[i] == 0)){
                newPixelValue[i] = histogram[i];
                rovMatchCount = 0;
            }
            else{
                rovMatchCount++;
                newPixelValue[i] = newPixelValue[i - 1] + histogram[i];
                for(int j = 1; j<=rovMatchCount; j++){
                    newPixelValue[i - j] = newPixelValue[i];
                }
            }
            System.out.println("ROV["+i+"]= "+rov[i]);
            
        }
        
        for(int i = 0; i<newPixelValue.length; i++){
            System.out.println("newPixelValue["+i+"]= "+newPixelValue[i]);
        }
        
        
        
        //output new image
        for (int row = 0; row <imageHeight; row++) {
            for (int col = 0; col < imageWidth; col++) {
                int pixel = newPixelValue[raster.getSample(col, row, 0)];
                raster.setSample(col, row, 0, pixel);
            }
        }
        input_image_buffer.setData(raster);
        output_image_view.setImage(SwingFXUtils.toFXImage(input_image_buffer, null));
        
        System.out.println("Sum of Histogram = "+Arrays.stream(histogram).sum());
        System.out.println("Sum Of New Pixel = " + Arrays.stream(newPixelValue).sum());
        
        

    }

}
