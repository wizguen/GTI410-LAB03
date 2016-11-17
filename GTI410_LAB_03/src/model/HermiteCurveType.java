package model;

import java.awt.Point;
import java.util.List;

public class HermiteCurveType extends CurveType {

	public HermiteCurveType(String name) {
		super(name);
	}
	
	public int getNumberOfSegments(int numberOfControlPoints) {
		if (numberOfControlPoints >= 4) {
			return (numberOfControlPoints - 1) / 3;
		} else {
			return 0;
		}
	}

	/* (non-Javadoc)
	 * @see model.CurveType#getNumberOfControlPointsPerSegment()
	 */
	public int getNumberOfControlPointsPerSegment() {
		return 4;
	}

	/* (non-Javadoc)
	 * @see model.CurveType#getControlPoint(java.util.List, int, int)
	 */
	public ControlPoint getControlPoint(
		List controlPoints,
		int segmentNumber,
		int controlPointNumber) {
		int controlPointIndex = segmentNumber * 3 + controlPointNumber;
		return (ControlPoint)controlPoints.get(controlPointIndex);
	}

	/* (non-Javadoc)
	 * @see model.CurveType#evalCurveAt(java.util.List, double)
	 */
	public Point evalCurveAt(List controlPoints, double t) {
				
		Point p1 = ((ControlPoint)controlPoints.get(0)).getCenter();
		Point p4 = ((ControlPoint)controlPoints.get(3)).getCenter();
		Point r1 = tangentePoint( p1 , ((ControlPoint)controlPoints.get(1)).getCenter());
		Point r4 = tangentePoint( p4 , ((ControlPoint)controlPoints.get(2)).getCenter());
		
		List tVector = Matrix.buildRowVector4(t*t*t, t*t, t, 1);		
		List gVector = Matrix.buildColumnVector4(p1,p4,r1,r4 );
		Point p = Matrix.eval(tVector, matrix, gVector);
		return p;
	}

	private List hermiteMatrix = 
		Matrix.buildMatrix4(2,  -2, 1, 1, 
							 -3, 3,  -2, -1, 
							0,  0,  1, 0, 
							 1,  0,  0, 0);
							 
	private List matrix = hermiteMatrix;
	
	
	private Point tangentePoint(Point p1, Point p2)
	{
		return new Point((int)(p1.getX() - p2.getX()),(int)(p1.getY() - p2.getY()));
	}

}
