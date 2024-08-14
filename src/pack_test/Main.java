package pack_test;
import java.io.File;
import java.lang.Thread;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.*;
import java.util.Scanner;

// 332AA Miu Vlad Alexandru
public class Main {
	 static
	    {
	        System.out.println("Mirror image !");
	        
	    }
	public static void main (String [] args) throws InterruptedException{
		BufferedImage img ;
		long start;  
		int Info[]=new int[4];//paddingSize, BitmapWidth, BitmapHeight, PixelArrayOffset
		
		System.out.println("Introduceti path-ul fisierului sursa: ");
		String path;
		
		Scanner scan = new Scanner(System.in);
        path = scan.nextLine();
        start = System.nanoTime(); 
         
        Info=Read.read(path); //citire informatii fisier  
        img=new BufferedImage(Info[1],Info[2],BufferedImage.TYPE_BYTE_INDEXED);//BufferedImage.TYPE_BYTE_INDEXED
      
        System.out.println("Timp citire info : " + (System.nanoTime() - start) + " nanosecunde");
        //citire imagine pe thread-uri Producer
        Multithreading thread1 = new Multithreading();
        Multithreading thread2 = new Multithreading();
        Multithreading thread3 = new Multithreading();
        Multithreading thread4 = new Multithreading();
        
        
        ///////////////////////// setare parametrii threaduri
        thread1.setWidth(Info[1]);
        thread1.setHeightIndex1(0);
        thread1.setHeightIndex2(Info[2]/4);
        thread1.setPaddingSize(Info[0]);
        thread1.setPixelArrayOffset(Info[3]);
        thread1.setImage(img);
        thread1.setPath(path);
        
        
        thread2.setWidth(Info[1]);
        thread2.setHeightIndex1(Info[2]/4);
        thread2.setHeightIndex2(Info[2]/2);
        thread2.setPaddingSize(Info[0]);
        thread2.setPixelArrayOffset(Info[3]);
        thread2.setImage(img);
        thread2.setPath(path);
        
        
        thread3.setWidth(Info[1]);
        thread3.setHeightIndex1(Info[2]/2);
        thread3.setHeightIndex2(Info[2]*3/4);
        thread3.setPaddingSize(Info[0]);
        thread3.setPixelArrayOffset(Info[3]);
        thread3.setImage(img);
        thread3.setPath(path);
        
        
        thread4.setWidth(Info[1]);
        thread4.setHeightIndex1(Info[2]*3/4);
        thread4.setHeightIndex2(Info[2]);
        thread4.setPaddingSize(Info[0]);
        thread4.setPixelArrayOffset(Info[3]);
        thread4.setImage(img);
        thread4.setPath(path);
        /////////////////////////////////////////
      
        
        
        ////////////////////
        thread1.start(); 
        try {             
            Thread.sleep(100);               
        }
        catch (Exception e) {
    	 System.out.println(e);
        }
        thread2.start();
        try {             
            Thread.sleep(100);               
        }
        catch (Exception e) {
    	 System.out.println(e);
        }
        thread3.start();
        try {             
                Thread.sleep(100);               
        }
        catch (Exception e) {
        	 System.out.println(e);
        }
        thread4.start();
        //
        thread1.join();
        thread2.join();
        thread3.join();
        thread4.join();
        System.out.println("Timp citire imagine : " + (System.nanoTime() - start) + " nanosecunde"); 
        /////////Consumer
        Multithreading2 thread21 = new Multithreading2();
        Multithreading2 thread22 = new Multithreading2();
        Multithreading2 thread23 = new Multithreading2();
        Multithreading2 thread24 = new Multithreading2();
        ///////
        thread21.setWidth(Info[1]);
        thread21.setHeightIndex1(0);
        thread21.setHeightIndex2(Info[2]/4);
        thread21.setImage(img);
      
        thread22.setWidth(Info[1]);
        thread22.setHeightIndex1(Info[2]/4);
        thread22.setHeightIndex2(Info[2]/2);
        thread22.setImage(img);
        
        thread23.setWidth(Info[1]);
        thread23.setHeightIndex1(Info[2]/2);
        thread23.setHeightIndex2(Info[2]*3/4);
        thread23.setImage(img);
        
        thread24.setWidth(Info[1]);
        thread24.setHeightIndex1(Info[2]*3/4);
        thread24.setHeightIndex2(Info[2]);
        thread24.setImage(img);
        //////////
        thread21.start(); 
 
        thread22.start();/*
  
        thread23.start();/*
    
        thread24.start();
        thread21.join();
        thread22.join();
        thread23.join();
        thread24.join();
        System.out.println("Timp procesar : " + (System.nanoTime() - start) + " nanosecunde");
        Image imagine = new Image();
        imagine.setImage(img);
        imagine.setPath(path);
        System.out.println("Introduceti path-ul fisierului destinatie: ");
		path = scan.nextLine();
		Write.write(imagine.getImage(),path);
		System.out.println("Timp scriere: " + (System.nanoTime() - start) + " nanosecunde");
}
}
