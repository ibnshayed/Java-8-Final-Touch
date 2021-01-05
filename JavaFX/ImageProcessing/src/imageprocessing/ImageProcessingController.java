/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imageprocessing;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;

/**
 *
 * @author User
 */
public class ImageProcessingController implements Initializable {

    @FXML
    private MenuItem menu_inport_image;
    @FXML
    private MenuItem convertion_rgb_to_cmy;
    @FXML
    private MenuItem conversion_rgb_to_hsi;
    @FXML
    private ImageView originalImageView;
    @FXML
    private ImageView outputImageView;

    private BufferedImage inputBufferedImage, outputBufferedImage;
    @FXML
    private MenuItem saveImage;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void actionImportImageFromComputer(ActionEvent event) {
        try {
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("Image File", "*.jpg")
            );
            File selectedFile = fileChooser.showOpenDialog(null);
            if (selectedFile != null) {
                Image image = new Image(new FileInputStream(selectedFile));
                originalImageView.setImage(image);
                inputBufferedImage = ImageIO.read(selectedFile);
            }
        } catch (FileNotFoundException ex) {
            System.err.println(ex.getMessage());
        } catch (IOException ex) {
            Logger.getLogger(ImageProcessingController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void actionSaveImageFromComputer(ActionEvent event) throws IOException {
        
        //FileOutputStream fileOutputStream = null;
        try {
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("JPG File", "*.jpg"),
                    new FileChooser.ExtensionFilter("PNG File","*.png")
            );
            File file = fileChooser.showSaveDialog(null);
            if (file != null) {
                //fileOutputStream = new FileOutputStream(file);
                if(ImageIO.write(outputBufferedImage, "jpg", file)){
                    System.out.println("Image saved");
                }else{
                    System.out.println("Image not saved");
                }
                //fileOutputStream.write(outputBufferedImage.toString().getBytes());
            }
        } catch (FileNotFoundException ex) {
            System.err.println(ex.getMessage());
        }finally{
            //fileOutputStream.close();
        }
    }

    @FXML
    private void actionConvertRGBToCMY(ActionEvent event) {
        outputBufferedImage = new BufferedImage(inputBufferedImage.getWidth(), inputBufferedImage.getHeight(), BufferedImage.TYPE_3BYTE_BGR);

        for (int row = 0; row < inputBufferedImage.getHeight(); row++) {
            for (int col = 0; col < inputBufferedImage.getWidth(); col++) {
                int rgb = inputBufferedImage.getRGB(col, row);

                int red = (rgb >> 16) & 0xFF;
                int green = (rgb >> 8) & 0xFF;
                int blue = (rgb) & 0xFF;

                int cyne = 255 - red;
                int megenta = 255 - green;
                int yellow = 255 - blue;

                outputBufferedImage.setRGB(col, row, (cyne << 16 | megenta << 8 | yellow));

            }
        }

        outputImageView.setImage(SwingFXUtils.toFXImage(outputBufferedImage, null));
    }

    @FXML
    private void actionConversionRGBToHSI(ActionEvent event) {

        outputBufferedImage = new BufferedImage(inputBufferedImage.getWidth(), inputBufferedImage.getHeight(), BufferedImage.TYPE_3BYTE_BGR);

        for (int row = 0; row < inputBufferedImage.getHeight(); row++) {
            for (int col = 0; col < inputBufferedImage.getWidth(); col++) {
                int rgb = inputBufferedImage.getRGB(col, row);

                int red = (rgb >> 16) & 0xFF;
                int green = (rgb >> 8) & 0xFF;
                int blue = (rgb) & 0xFF;

                int hue = 0;
                int saturation = 0;
                int intensity;
                int theta;

                intensity = (red + green + blue) / 3;

                theta = (int) Math.acos((((red - green) + (red - blue)) * 0.5) / Math.sqrt(Math.pow((red - green), 2) + ((red - blue) * (green - blue))));

                if (blue <= green) {
                    hue = theta;
                }
                if (blue > green) {
                    hue = 360 - theta;
                }

                if (intensity == 0) {
                    saturation = 0;
                }
                if (intensity != 0) {
                    saturation = 1 - ((3 / (red + green + blue)) * Math.min(Math.min(red, green), blue));
                }

                outputBufferedImage.setRGB(col, row, (hue << 16 | saturation << 8 | intensity));
            }
        }

        outputImageView.setImage(SwingFXUtils.toFXImage(outputBufferedImage, null));
    }

    @FXML
    private void actionConversionRGBToGrayScaleByAverageAlgorithm(ActionEvent event) {
        outputBufferedImage = new BufferedImage(inputBufferedImage.getWidth(), inputBufferedImage.getHeight(), BufferedImage.TYPE_BYTE_GRAY);

        for (int row = 0; row < inputBufferedImage.getHeight(); row++) {
            for (int col = 0; col < inputBufferedImage.getWidth(); col++) {
                int rgb = inputBufferedImage.getRGB(col, row);

                int red = (rgb >> 16) & 0xFF;
                int green = (rgb >> 8) & 0xFF;
                int blue = (rgb) & 0xFF;

                int gray = (red + green + blue) / 3;

                outputBufferedImage.setRGB(col, row, (gray << 16 | gray << 8 | gray));

            }
        }

        outputImageView.setImage(SwingFXUtils.toFXImage(outputBufferedImage, null));

    }

    @FXML
    private void actionConversionRGBToGrayScaleByLumaAlgorithm(ActionEvent event) {
        outputBufferedImage = new BufferedImage(inputBufferedImage.getWidth(), inputBufferedImage.getHeight(), BufferedImage.TYPE_BYTE_GRAY);

        for (int row = 0; row < inputBufferedImage.getHeight(); row++) {
            for (int col = 0; col < inputBufferedImage.getWidth(); col++) {
                int rgb = inputBufferedImage.getRGB(col, row);

                int red = (rgb >> 16) & 0xFF;
                int green = (rgb >> 8) & 0xFF;
                int blue = (rgb) & 0xFF;

                int gray = (int) ((red * 0.3) + (green * 0.59) + (blue * 0.11)) / 3;

                outputBufferedImage.setRGB(col, row, (gray << 16 | gray << 8 | gray));

            }
        }

        outputImageView.setImage(SwingFXUtils.toFXImage(outputBufferedImage, null));
    }

    @FXML
    private void actionConversionRGBToGrayScaleByDesaturationAlgorithm(ActionEvent event) {
        outputBufferedImage = new BufferedImage(inputBufferedImage.getWidth(), inputBufferedImage.getHeight(),BufferedImage.TYPE_BYTE_GRAY);

        for (int row = 0; row < inputBufferedImage.getHeight(); row++) {
            for (int col = 0; col < inputBufferedImage.getWidth(); col++) {
                int rgb = inputBufferedImage.getRGB(col, row);

                int red = (rgb >> 16) & 0xFF;
                int green = (rgb >> 8) & 0xFF;
                int blue = (rgb) & 0xFF;

                int gray = (Math.max(Math.max(red, green), blue) + Math.min(Math.min(red, green), blue)) / 2;

                outputBufferedImage.setRGB(col, row, (gray << 16 | gray << 8 | gray));

            }
        }

        outputImageView.setImage(SwingFXUtils.toFXImage(outputBufferedImage, null));
    }

    @FXML
    private void actionConversionRGBToGrayScaleByDecompositionAlgorithm(ActionEvent event) {
        outputBufferedImage = new BufferedImage(inputBufferedImage.getWidth(), inputBufferedImage.getHeight(),BufferedImage.TYPE_BYTE_GRAY);

        for (int row = 0; row < inputBufferedImage.getHeight(); row++) {
            for (int col = 0; col < inputBufferedImage.getWidth(); col++) {
                int rgb = inputBufferedImage.getRGB(col, row);

                int red = (rgb >> 16) & 0xFF;
                int green = (rgb >> 8) & 0xFF;
                int blue = (rgb) & 0xFF;

                int gray = Math.max(Math.max(red, green), blue);
                //int gray = Math.min(Math.min(red, green), blue);

                outputBufferedImage.setRGB(col, row, (gray << 16 | gray << 8 | gray));

            }
        }

        outputImageView.setImage(SwingFXUtils.toFXImage(outputBufferedImage, null));

    }

    @FXML
    private void actionConversionToRed(ActionEvent event) {
        outputBufferedImage = new BufferedImage(inputBufferedImage.getWidth(), inputBufferedImage.getHeight(), BufferedImage.TYPE_BYTE_GRAY);

        for (int row = 0; row < inputBufferedImage.getHeight(); row++) {
            for (int col = 0; col < inputBufferedImage.getWidth(); col++) {
                int rgb = inputBufferedImage.getRGB(col, row);

                int red = (rgb >> 16) & 0xFF;
                int green = (rgb >> 8) & 0xFF;
                int blue = (rgb) & 0xFF;

                outputBufferedImage.setRGB(col, row, (red << 16 | red<< 8 | red));

            }
        }

        outputImageView.setImage(SwingFXUtils.toFXImage(outputBufferedImage, null));
    }

    @FXML
    private void actionConversionToGreen(ActionEvent event) {
        outputBufferedImage = new BufferedImage(inputBufferedImage.getWidth(), inputBufferedImage.getHeight(),BufferedImage.TYPE_BYTE_GRAY);

        for (int row = 0; row < inputBufferedImage.getHeight(); row++) {
            for (int col = 0; col < inputBufferedImage.getWidth(); col++) {
                int rgb = inputBufferedImage.getRGB(col, row);

                int red = (rgb >> 16) & 0xFF;
                int green = (rgb >> 8) & 0xFF;
                int blue = (rgb) & 0xFF;

                outputBufferedImage.setRGB(col, row, (green << 16 | green << 8 | green));

            }
        }

        outputImageView.setImage(SwingFXUtils.toFXImage(outputBufferedImage, null));
    }

    @FXML
    private void actionConversionToBlue(ActionEvent event) {
        outputBufferedImage = new BufferedImage(inputBufferedImage.getWidth(), inputBufferedImage.getHeight(), BufferedImage.TYPE_BYTE_GRAY);

        for (int row = 0; row < inputBufferedImage.getHeight(); row++) {
            for (int col = 0; col < inputBufferedImage.getWidth(); col++) {
                int rgb = inputBufferedImage.getRGB(col, row);

                int red = (rgb >> 16) & 0xFF;
                int green = (rgb >> 8) & 0xFF;
                int blue = (rgb) & 0xFF;

                outputBufferedImage.setRGB(col, row, (blue << 16 | blue << 8 | blue));

            }
        }

        outputImageView.setImage(SwingFXUtils.toFXImage(outputBufferedImage, null));
    }

    

}
