package pack_test;

import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
public class Write {
	public static void write (BufferedImage img, String path) {
		try{
            if(!path.endsWith(".bmp")) {
                System.out.println("Fisierul trebuie sa contina extensia .bmp!"); // tratare eroare de scriere
                System.exit(0);
            }
			File f = new File(path);
            ImageIO.write(img, "bmp", f);
            System.out.println("Fisier !"); 
            
        }catch(IOException e){
        	System.out.println("Scriere nereusita!"); // tratare eroare de scriere
            System.exit(0);
        }
	}
}