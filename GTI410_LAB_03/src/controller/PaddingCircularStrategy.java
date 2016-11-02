package controller;

import model.ImageDouble;
import model.ImageX;
import model.Pixel;
import model.PixelDouble;

public class PaddingCircularStrategy extends PaddingStrategy{
	
	//http://xphilipp.developpez.com/articles/filtres/?page=page_3
	// Image[-1][y]= Image[Largeur-1][y]   circulaire
	public Pixel pixelAt(ImageX img, int x, int y) 
	{
		// With
		if(x < 0) 
		{ 
			x = 1; 
		}
		else if (x >= img.getImageWidth())
		{
			x = img.getImageWidth()-1;
		}
		// Height
		if(y < 0) 
		{ 
			y = 1; 
		}
		else if (y >= img.getImageHeight())
		{ 
			y = img.getImageHeight()-1; 
		}

		return img.getPixel(x, y);
	}

	
	
	public PixelDouble pixelAt(ImageDouble img, int x, int y) 
	{
		
		// With
		if(x < 0) 
		{ 
			x = 2; 
		}
		else if (x >= img.getImageWidth())
		{
			x = img.getImageWidth()-1;
		}
		// Height
		if(y < 0) 
		{ 
			y = 2; 
		}
		else if (y >= img.getImageHeight())  
		{ 
			y = img.getImageHeight()-1; 
		}
		return img.getPixel(x, y);
	}

}
