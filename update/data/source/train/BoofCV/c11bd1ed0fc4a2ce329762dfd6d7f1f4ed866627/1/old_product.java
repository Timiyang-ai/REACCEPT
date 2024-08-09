public static Rectangle2D_I32 boundBox( int srcWidth , int srcHeight ,
											int dstWidth , int dstHeight ,
											PixelTransform_F32 transform )
	{
		Rectangle2D_I32 ret = boundBox(srcWidth,srcHeight,transform);

		int x0 = ret.tl_x;
		int y0 = ret.tl_y;
		int x1 = ret.tl_x + ret.width;
		int y1 = ret.tl_y + ret.height;

		if( x0 < 0 ) x0 = 0;
		if( x1 > dstWidth) x1 = dstWidth;
		if( y0 < 0 ) y0 = 0;
		if( y1 > dstHeight) y1 = dstHeight;

		return new Rectangle2D_I32(x0,y0,x1-x0,y1-y0);
	}