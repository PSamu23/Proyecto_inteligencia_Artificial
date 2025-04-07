
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.Color;
import java.io.File;
import java.io.IOException;

public class ImageProcessor {
    public static double[] processImage(String path) {
        try {
            BufferedImage img = ImageIO.read(new File(path));
            BufferedImage scaled = new BufferedImage(600, 800, BufferedImage.TYPE_BYTE_GRAY);
            scaled.getGraphics().drawImage(img, 0, 0, 600, 800, null);
            double[] pixels = new double[600 * 800];
            for (int y = 0; y < 800; y++) {
                for (int x = 0; x < 600; x++) {
                    int rgb = scaled.getRGB(x, y);
                    int gray = new Color(rgb).getRed();
                    pixels[y * 600 + x] = gray / 255.0;
                }
            }
            return pixels;
        } catch (IOException e) {
            System.err.println("Error al lleer la imgen: " + e.getMessage());
            return null;
        }
    }
}
