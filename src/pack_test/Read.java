package pack_test;
import java.io.File;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Read extends Image{
		public static int[] read (String path){
			try{
				InputStream inputStream = new FileInputStream(path);
				int Bytes[]= new int[4];
				// Read bitmap type
				Bytes[0] = inputStream.read();
				Bytes[1] = inputStream.read();
				if (Bytes[0] == -1 || Bytes[1] == -1)
					throw new IOException("Invalid bmp format.");
				
				if (Bytes[0] != 'B' || Bytes[1] != 'M')
					throw new IOException("Invalid bmp format.");
				
				// Read bitmap size
				Bytes[0] = inputStream.read();
				Bytes[1] = inputStream.read();
				Bytes[2] = inputStream.read();
				Bytes[3] = inputStream.read();
				if (Bytes[0] == -1 || Bytes[1] == -1 || Bytes[2] == -1 || Bytes[3] == -1)
					throw new IOException("Invalid bmp format.");
				
				int FileSize =  ((int)Bytes[3] << 24) + ((int)Bytes[2] << 16) +((int)Bytes[1] <<  8) + (int)Bytes[0];
				// System.out.println("FielSize"+FileSize);
				// Read 4 reserved bits.
				Bytes[0] = inputStream.read();
				Bytes[0] = inputStream.read();
				Bytes[0] = inputStream.read();
				Bytes[0] = inputStream.read();
				
				// Read offset for bitmap image data.
				Bytes[0] = inputStream.read();
				Bytes[1] = inputStream.read();
				Bytes[2] = inputStream.read();
				Bytes[3] = inputStream.read();
				if (Bytes[0] == -1 || Bytes[1] == -1 || Bytes[2] == -1 || Bytes[3] == -1)
					throw new IOException("Invalid bmp format.");
				
				int PixelArrayOffset =  ((int)Bytes[3] << 24) +((int)Bytes[2] << 16) +((int)Bytes[1] <<  8) + (int)Bytes[0];
				
				// Bitmap info header
				
				
				// Read bih size
				Bytes[0] = inputStream.read();
				Bytes[1] = inputStream.read();
				Bytes[2] = inputStream.read();
				Bytes[3] = inputStream.read();
				if (Bytes[0] == -1 || Bytes[1] == -1 || Bytes[2] == -1 || Bytes[3] == -1)
					throw new IOException("Invalid bmp format.");
				
				int BitmapInfoSize = ((int)Bytes[3] << 24) +((int)Bytes[2] << 16) +((int)Bytes[1] <<  8) +(int)Bytes[0];
				
				// Read width
				Bytes[0] = inputStream.read();
				Bytes[1] = inputStream.read();
				Bytes[2] = inputStream.read();
				Bytes[3] = inputStream.read();
				if (Bytes[0] == -1 || Bytes[1] == -1 || Bytes[2] == -1 || Bytes[3] == -1)
					throw new IOException("Invalid bmp format.");
				
				int BitmapWidth = ((int)Bytes[3] << 24) +((int)Bytes[2] << 16) +((int)Bytes[1] <<  8) + (int)Bytes[0];	
				
				// Read height
				Bytes[0] = inputStream.read();
				Bytes[1] = inputStream.read();
				Bytes[2] = inputStream.read();
				Bytes[3] = inputStream.read();
				if (Bytes[0] == -1 || Bytes[1] == -1 || Bytes[2] == -1 || Bytes[3] == -1)
					throw new IOException("Invalid bmp format.");
							
				int BitmapHeight = ((int)Bytes[3] << 24) + ((int)Bytes[2] << 16) + ((int)Bytes[1] <<  8) + (int)Bytes[0];
				
				// Read and ignore number of color planes
				Bytes[0] = inputStream.read();
				Bytes[0] = inputStream.read();
				
				// Number of bits per pixel
				Bytes[0] = inputStream.read();
				Bytes[1] = inputStream.read();
				if (Bytes[0] == -1 || Bytes[1] == -1)
					throw new IOException("Invalid bmp format.");
				
				int BitsPerPixel =  ((int)Bytes[1] << 8) + ((int)Bytes[0]);
				
				// Pixel compression method
				Bytes[0] = inputStream.read();
				Bytes[1] = inputStream.read();
				Bytes[2] = inputStream.read();
				Bytes[3] = inputStream.read();
				if (Bytes[0] == -1 || Bytes[1] == -1 || Bytes[2] == -1 || Bytes[3] == -1)
					throw new IOException("Invalid bmp format.");
				int PixelCompression =  ((int)Bytes[3] << 24) + ((int)Bytes[2] << 16) + ((int)Bytes[1] <<  8) + (int)Bytes[0];
				
				if (PixelCompression != 0)
					throw new IOException("Bmp is compressed.");
					
										
				// Discard anything else until PixelArrayInfo
				for(int r = 38; r < PixelArrayOffset; r++)
					Bytes[0] = inputStream.read();
				
				// Calculate padding size
				int paddingSize = (4 - (BitmapWidth*3)%4)%4;
				
				//inputStream.close();
				return new int[] {paddingSize,BitmapWidth,BitmapHeight,PixelArrayOffset};
			}catch(IOException e){
				//System.out.println("Exception s");
				e.printStackTrace(System.out);
				return null;
			}
		}
}
