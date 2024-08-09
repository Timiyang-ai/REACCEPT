public static RectangleLength2D_F32 centerBoxInside(int srcWidth, int srcHeight,
														PixelTransform2_F32 transform) {

		List<Point2D_F32> points = computeBoundingPoints(srcWidth, srcHeight, transform);

		Point2D_F32 center = new Point2D_F32();
		UtilPoint2D_F32.mean(points,center);

		float x0,x1,y0,y1;
		float bx0,bx1,by0,by1;
		x0=x1=y0=y1=0;
		bx0=bx1=by0=by1=Float.MAX_VALUE;

		for (int i = 0; i < points.size(); i++) {
			Point2D_F32 p = points.get(i);
			float dx = p.x-center.x;
			float dy = p.y-center.y;
			float adx = (float)Math.abs(dx);
			float ady = (float)Math.abs(dy);

			if( adx < ady ) {
				if( dy < 0 ) {
					if( adx < by0 ) {
						by0 = adx;
						y0 = dy;
					}
 				} else {
					if( adx < by1 ) {
						by1 = adx;
						y1 = dy;
					}
				}
			} else {
				if( dx < 0 ) {
					if( ady < bx0 ) {
						bx0 = ady;
						x0 = dx;
					}
				} else {
					if( ady < bx1 ) {
						bx1 = ady;
						x1 = dx;
					}
				}

			}
		}

		return new RectangleLength2D_F32(x0+center.x,y0+center.y,x1-x0,y1-y0);
	}