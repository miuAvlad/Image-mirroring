package pack_test;
import java.awt.image.BufferedImage;


import java.io.IOException;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
public class Image implements Interface {
	protected BufferedImage img;	
	private String path;
	//protected int width;
	//protected int height;
	public void setPath(String pth) {
		this.path = pth;
	}
	public String getPath() {
		return this.path;
	}	
	public void setImage(BufferedImage image) {
		this.img = image;
	}	
	public BufferedImage getImage() {
		return img;
	}
	public int getWidth(){
		return img.getWidth();
	}
	public int getHeight(){
		return img.getHeight();
	}
}
