package pack_test;

import java.awt.image.BufferedImage;
import java.io.IOException;

public class Multithreading2 extends Thread{
	
	protected BufferedImage img;
	protected int Width ;
	protected int HeightIndex1 ;
	protected int HeightIndex2;
	@Override
	public void run(){
		try{
			if( img == null)
				throw new IOException("null image");
			
			for(int i=HeightIndex1;i<HeightIndex2;i++){	
				for(int j=0;j<Width/2;j++){
				    int p = img.getRGB(j, i);
				   // System.out.println(" val pixel " +p);
				    img.setRGB(j, i, img.getRGB(Width-1-j, i));
				    img.setRGB(Width-1-j, i, p);
				}
			
			}
			System.out.println(" height1 "+ HeightIndex1+ " height2 "+HeightIndex2);
		}catch(IOException e){
			e.printStackTrace(System.out);
		}
	}
	public void setImage (BufferedImage imagine){
		img=imagine;
	}
	public void setHeightIndex1 (int height){
		HeightIndex1=height;
	}
	public void setHeightIndex2 (int height){
		HeightIndex2=height;
	}
	public void setWidth (int width){
		Width=width;
	}
	
}
