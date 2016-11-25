package model;

import java.awt.Point;
import java.util.List;

public class BSplineCurveType extends CurveType{

	public BSplineCurveType(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getNumberOfSegments(int numberOfControlPoints) {
		// TODO Auto-generated method stub
		if (numberOfControlPoints >= 4)
			return numberOfControlPoints - 3;
		else
			return 0;
	}

	@Override
	public int getNumberOfControlPointsPerSegment() {
		// TODO Auto-generated method stub
		return 4;
	}

	@Override
	public ControlPoint getControlPoint(List controlPoints, int segmentNumber, int controlPointNumber) {
		// TODO Auto-generated method stub
		int controlPointIndex = segmentNumber + controlPointNumber;
			
		return (ControlPoint)controlPoints.get(controlPointIndex);
	}
	
	private List bsplineMatrix = 
			Matrix.buildMatrix4(-1.0/6.0,	3.0/6.0,	-3.0/6.0,	1.0/6.0, 
								 3.0/6.0,	-6.0/6.0, 	3.0/6.0,	0.0/6.0, 
								-3.0/6.0,	0.0/6.0,	3.0/6.0,	0.0/6.0, 
								 1.0/6.0,	4.0/6.0,	1.0/6.0,	0/6.0);
	
	private List matrix = bsplineMatrix;
	
	@Override
	public Point evalCurveAt(List controlPoints, double t) {
		// TODO Auto-generated method stub
		List tVector = Matrix.buildRowVector4(t*t*t, t*t, t, 1);
		
		Point p1 = ((ControlPoint)controlPoints.get(0)).getCenter();
		Point p2 = ((ControlPoint)controlPoints.get(1)).getCenter();
		Point p3 = ((ControlPoint)controlPoints.get(2)).getCenter();
		Point p4 = ((ControlPoint)controlPoints.get(3)).getCenter();
		
		List gVector = Matrix.buildColumnVector4(p1, p2, p3, p4);
		
		Point p = Matrix.eval(tVector, matrix, gVector);
		
		return p;
		}
	
}
