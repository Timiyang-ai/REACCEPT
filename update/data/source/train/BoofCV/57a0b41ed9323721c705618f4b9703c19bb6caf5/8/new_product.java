static public void naive4(GrayF32 _intensity , GrayS8 direction , GrayF32 output )
	{
		final int w = _intensity.width;
		final int h = _intensity.height;

		ImageBorder_F32 intensity = (ImageBorder_F32)FactoryImageBorderAlgs.value(_intensity, 0);

		for( int y = 0; y < h; y++ ) {
			for( int x = 0; x < w; x++ ) {
				int dir = direction.get(x,y);
				int dx,dy;

				if( dir == 0 ) {
					dx = 1; dy = 0;
				} else if( dir == 1 ) {
					dx = 1; dy = 1;
				} else if( dir == 2 ) {
					dx = 0; dy = 1;
				} else {
					dx = 1; dy = -1;
				}

				float left = intensity.get(x-dx,y-dy);
				float middle = intensity.get(x,y);
				float right = intensity.get(x+dx,y+dy);

				// suppress the value if either of its neighboring values are more than or equal to it
				if( left > middle || right > middle ) {
					output.set(x,y,0);
				} else {
					output.set(x,y,middle);
				}
			}
		}

	}