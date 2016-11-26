package utilities;

import java.awt.Point;

import model.Shape;

public final class AnchorUtility {
	
	public static Point determineAnchorPoint(int anchor, Shape shape)
	{

		//0-------1------2
		//|		  |		 |
		//|		  |		 |
		//3-------4------5
		//|		  |		 |
		//|		  |		 |
		//6-------7------8
		
			
		Point p = new Point();
		switch(anchor)
		{
		case 0:
			p.setLocation(0, 0);
			break;
		case 1:
			p.setLocation((int)shape.getCenter().getX(), 0);;
			break;
		case 2:
			p.setLocation((int)shape.getCenter().getX() *2, 0);
			break;
		case 3:
			p.setLocation(0, (int)shape.getCenter().getY());
			break;
		case 4:
			p.setLocation((int)shape.getCenter().getX(), (int)shape.getCenter().getY());
			break;
		case 5:
			p.setLocation((int)shape.getCenter().getX() *2, (int)shape.getCenter().getY());
			break;
		case 6:
			p.setLocation(0, (int)shape.getCenter().getY() *2);
			break;
		case 7:
			p.setLocation((int)shape.getCenter().getX(), (int)shape.getCenter().getY() *2);
			break;
		case 8:
			p.setLocation((int)shape.getCenter().getX() * 2, (int)shape.getCenter().getY() *2);
			break;
		default:
			p = null;
		}
		return p;
		
	}

}
