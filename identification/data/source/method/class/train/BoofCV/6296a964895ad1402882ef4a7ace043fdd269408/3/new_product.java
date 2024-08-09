public static RectangleLength2D_I32 boundBox( int srcWidth , int srcHeight ,
											PixelTransform_F32 transform )
	{
		int x0,y0,x1,y1;

		transform.compute(0,0);
		x0=x1=(int)transform.distX;
		y0=y1=(int)transform.distY;

		for( int i = 1; i < 4; i++ ) {
			if( i == 1 )
				transform.compute(srcWidth,0);
			else if( i == 2 )
				transform.compute(0,srcHeight);
			else if( i == 3 )
				transform.compute(srcWidth-1,srcHeight);

			if( transform.distX < x0 )
				x0 = (int)transform.distX;
			else if( transform.distX > x1 )
				x1 = (int)transform.distX;
			if( transform.distY < y0 )
				y0 = (int)transform.distY;
			else if( transform.distY > y1 )
				y1 = (int)transform.distY;
		}

		return new RectangleLength2D_I32(x0,y0,x1-x0,y1-y0);
	}