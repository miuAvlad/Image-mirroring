package pack_test;

import java.io.File;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Multithreading extends Thread {
	protected BufferedImage img;
	protected int Width ;
	protected int HeightIndex1 ;
	protected int HeightIndex2;
	protected int PaddingSize;
	protected int PixelArrayOffset;
	protected String Path;


	@Override
	public void run(){
		try{
			if( img == null)
				throw new IOException("null image");
			InputStream inputStream = new FileInputStream(Path);
			int Bytes[]= new int[4];
			for(int r = 0; r < PixelArrayOffset ; r++)
				Bytes[0] = inputStream.read();
			for(int v = 0;v<Width*HeightIndex1;v++){
				Bytes[0] = inputStream.read();
				Bytes[1] = inputStream.read();
				Bytes[2] = inputStream.read();
			}
			for(int x=0;x<HeightIndex1;x++){
				for(int y=0;y<PaddingSize;y++){
					Bytes[0] = inputStream.read();
				}
			}
					
			///////////////////////////////////////////////////////////////////
			
			
			for(int h = HeightIndex1 ; h < HeightIndex2; h++)
			{
				for(int w = 0; w < Width; w++)
				{
					Bytes[0] = inputStream.read();
					Bytes[1] = inputStream.read();
					Bytes[2] = inputStream.read();
					//ReadByte[3] = inputStream.read();
					int rgb=(Bytes[2]<< 16) | (Bytes[1] << 8) | Bytes[0];
					if (Bytes[0] == -1 || Bytes[1] == -1 || Bytes[2] == -1 || Bytes[3] == -1)
						throw new IOException("Invalid bmp format.");
					    
                        img.setRGB(w,img.getHeight()-1-h, rgb);
						//img.setRGB(img.getWidth()-1-w, img.getHeight()-1-h, rgb);
                        
					
					
				}	
				for(int w = 0; w < PaddingSize; w++)
					Bytes[0] = inputStream.read();
			}
			System.out.println(" height1 "+ HeightIndex1+ " height2 "+HeightIndex2);
			
			/////////////////////////////////////////////////////////////////////
		}catch(IOException e){
			e.printStackTrace(System.out);
		}
	}
	public void setWidth(int width){
		Width=width;
	}
	public void setHeightIndex1(int index){
		HeightIndex1=index;
	}
	public void setHeightIndex2(int index){
		HeightIndex2=index;
	}
	public void setPaddingSize(int padding){
		PaddingSize=padding;
	}
	public void setPixelArrayOffset(int pixeloffset){
		PixelArrayOffset=pixeloffset;
	}
	public void setPath(String path){
		Path=path;
	}
	public void setImage(BufferedImage image){
		img=image;
	}
	public BufferedImage getImage(){
		return img;
	}
	
}
