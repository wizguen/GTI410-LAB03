package controller;

import model.ImageDouble;
import model.ImageX;
import model.Pixel;
import model.PixelDouble;

public class ImageNormAbStrategy extends ImageConversionStrategy {
	
	/**
	 * Converts an ImageDouble to an ImageX using a clamping strategy (0-255).
	 */
	//http://stackoverflow.com/questions/695084/how-do-i-normalize-an-image
	public ImageX convert(ImageDouble img) 
	{
		int imageWidth = img.getImageWidth();
		int imageHeight = img.getImageHeight();
		ImageX newImage = new ImageX(0, 0, imageWidth, imageHeight);
		PixelDouble curPixelDouble = null;
		

	//	newImage.beginPixelUpdate();
		PixelDouble maxPixel = new PixelDouble();
		// Chercher la valeur de pixel la plus élevé
		for (int x = 0; x < imageWidth; x++) {
			for (int y = 0; y < imageHeight; y++) {
				curPixelDouble = img.getPixel(x,y);
				
				if (Math.abs(curPixelDouble.getRed()) > maxPixel.getRed() 
						&& Math.abs(curPixelDouble.getBlue()) > maxPixel.getBlue()
						&& Math.abs(curPixelDouble.getGreen()) > maxPixel.getGreen()) 
				{
					maxPixel.setRed(Math.abs(curPixelDouble.getRed()));
					maxPixel.setBlue(Math.abs(curPixelDouble.getBlue()));
					maxPixel.setGreen(Math.abs(curPixelDouble.getGreen()));
					maxPixel.setAlpha(Math.abs(curPixelDouble.getAlpha()));
				}
			}
		}
		
		//Normaliser 
		
		newImage.beginPixelUpdate();
		for (int x = 0; x < imageWidth; x++) 
		{
			for (int y = 0; y < imageHeight; y++) 
			{
				curPixelDouble = img.getPixel(x,y);
				
				newImage.setPixel(x, y, new Pixel((absNormalisation(Math.abs(curPixelDouble.getRed()), maxPixel.getRed())),
												  (absNormalisation(Math.abs(curPixelDouble.getGreen()), maxPixel.getBlue())),
												  (absNormalisation(Math.abs(curPixelDouble.getBlue()), maxPixel.getGreen())),
												  (absNormalisation(Math.abs(curPixelDouble.getAlpha()), maxPixel.getAlpha()))));
			}
		}
		newImage.endPixelUpdate();
		
		return newImage;
	}
	
	public int absNormalisation(double value, double max) {
		int norm = (int) (255.0 * value / max);
			
		return norm;
	}

}
