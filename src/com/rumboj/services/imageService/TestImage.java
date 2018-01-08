package com.rumboj.services.imageService;

public class TestImage {
	public static void main(String args[]) {
		 String inputImagePath = "D:/Photo/Puppy.jpg";
	        String outputImagePath1 = "D:/Photo/Puppy_Fixed.jpg";
	        String outputImagePath2 = "D:/Photo/Puppy_Smaller.jpg";
	        String outputImagePath3 = "D:/Photo/Puppy_Bigger.jpg";
	 
	        try {
	            // resize to a fixed width (not proportional)
	            int scaledWidth = 1024;
	            int scaledHeight = 768;
	            ImageResizer.resize(inputImagePath, outputImagePath1, scaledWidth, scaledHeight);
	 
	            // resize smaller by 50%
	            double percent = 0.5;
	            ImageResizer.resize(inputImagePath, outputImagePath2, percent);
	 
	            // resize bigger by 50%
	            percent = 1.5;
	            ImageResizer.resize(inputImagePath, outputImagePath3, percent);
	 
	        } catch (IOException ex) {
	            System.out.println("Error resizing the image.");
	            ex.printStackTrace();
	        }
	}
}
