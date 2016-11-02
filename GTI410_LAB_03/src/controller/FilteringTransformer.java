/*
   This file is part of j2dcg.
   j2dcg is free software; you can redistribute it and/or modify
   it under the terms of the GNU General Public License as published by
   the Free Software Foundation; either version 2 of the License, or
   (at your option) any later version.
   j2dcg is distributed in the hope that it will be useful,
   but WITHOUT ANY WARRANTY; without even the implied warranty of
   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
   GNU General Public License for more details.
   You should have received a copy of the GNU General Public License
   along with j2dcg; if not, write to the Free Software
   Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
*/
package controller;

import java.awt.event.MouseEvent;
import java.util.List;

import model.ImageDouble;
import model.ImageX;
import model.Shape;

/**
 * 
 * <p>Title: FilteringTransformer</p>
 * <p>Description: ... (AbstractTransformer)</p>
 * <p>Copyright: Copyright (c) 2004 S�bastien Bois, Eric Paquette</p>
 * <p>Company: (�TS) - �cole de Technologie Sup�rieure</p>
 * @author unascribed
 * @version $Revision: 1.6 $
 */
public class FilteringTransformer extends AbstractTransformer{
	//Filter filter = new MeanFilter3x3(new PaddingZeroStrategy(), new ImageClampStrategy());
	GFilter3x3 filter = new GFilter3x3(new PaddingZeroStrategy(), new ImageClampStrategy());
	/**
	 * @param _coordinates
	 * @param _value
	 */
	public void updateKernel(Coordinates _coordinates, float _value) {
		System.out.println("[" + (_coordinates.getColumn() - 1) + "]["
                                   + (_coordinates.getRow() - 1) + "] = " 
                                   + _value);
		filter.updateKernel( _coordinates, _value);
	}
	
		
	/**
	 * 
	 * @param e
	 * @return
	 */
	protected boolean mouseClicked(MouseEvent e){
		List intersectedObjects = Selector.getDocumentObjectsAtLocation(e.getPoint());
		if (!intersectedObjects.isEmpty()) {			
			Shape shape = (Shape)intersectedObjects.get(0);			
			if (shape instanceof ImageX) {				
				ImageX currentImage = (ImageX)shape;
				ImageDouble filteredImage = filter.filterToImageDouble(currentImage);
				ImageX filteredDisplayableImage = filter.getImageConversionStrategy().convert(filteredImage);
				currentImage.beginPixelUpdate();
				
				for (int i = 0; i < currentImage.getImageWidth(); ++i) {
					for (int j = 0; j < currentImage.getImageHeight(); ++j) {
						currentImage.setPixel(i, j, filteredDisplayableImage.getPixelInt(i, j));
					}
				}
				currentImage.endPixelUpdate();
			}
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see controller.AbstractTransformer#getID()
	 */
	public int getID() { return ID_FILTER; }

	/**
	 * @param string
	 */
	public void setBorder(String string) 
	{
		System.out.println("--------------------------------------------");
		System.out.println("Bordure choisi   " + string);
		System.out.println("--------------------------------------------");
		
		//http://xphilipp.developpez.com/articles/filtres/?page=page_3
		
		switch (string)
		{
		case "0" :
			filter.setPaddingStrategy(new PaddingZeroStrategy() );
			System.out.println("STRATEGIE MISE � ZERO");
			break;
		case "Circular":
			filter.setPaddingStrategy(new PaddingCircularStrategy() );
			System.out.println("STRATEGIE CIRCULAIRE");
			break;
		case "Mirror":
			System.out.println("STRATEGIE "+ string + " non impl�ment�e");
			break;
		case "Copy":
			System.out.println("STRATEGIE "+ string + " non impl�ment�e");
			break;
		}
		
	}

	/**
	 * @param string
	 */
	public void setClamp(String string) {
		System.out.println(string);
		switch (string) {
		case "Clamp 0...255":
			filter.setImageConversionStrategy(new ImageClampStrategy());
			break;
		case "Abs and normalize to 255":
			filter.setImageConversionStrategy(new ImageNormAbStrategy());
			System.out.println("STRATEGIE VALEUR ABSOLU");
			break;
		}
	}
}
