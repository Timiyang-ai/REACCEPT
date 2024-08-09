public void process() {

		int width = widthSubregion*widthGrid;
		int radius = width/2;

		int X0 = radius,X1 = savedAngle.width-radius;
		int Y0 = radius,Y1 = savedAngle.height-radius;

		int numX = (int)((X1-X0)/periodColumns);
		int numY = (int)((Y1-Y0)/periodRows);

		descriptors.reset();

		for (int i = 0; i < numY; i++) {
			int y = (Y1-Y0)*i/(numY-1) + Y0;

			for (int j = 0; j < numX; j++) {
				int x = (X1-X0)*j/(numX-1) + X0;

				TupleDesc_F64 desc = descriptors.grow();

				computeDescriptor(x,y,desc);
			}
		}
	}