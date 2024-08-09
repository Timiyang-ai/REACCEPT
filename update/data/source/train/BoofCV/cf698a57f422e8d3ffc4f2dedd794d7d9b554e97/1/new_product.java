public static RectangleLength2D_I32 boundBox( int srcWidth , int srcHeight ,
												  int dstWidth , int dstHeight ,
												  Point2D_F32 work,
												  PixelTransform<Point2D_F32> transform )
	{
		RectangleLength2D_I32 ret = boundBox(srcWidth,srcHeight,work,transform);

		int x0 = ret.x0;
		int y0 = ret.y0;
		int x1 = ret.x0 + ret.width;
		int y1 = ret.y0 + ret.height;

		if( x0 < 0 ) x0 = 0;
		if( x1 > dstWidth) x1 = dstWidth;
		if( y0 < 0 ) y0 = 0;
		if( y1 > dstHeight) y1 = dstHeight;

		return new RectangleLength2D_I32(x0,y0,x1-x0,y1-y0);
	}