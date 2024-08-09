public void process(D derivX , D derivY ) {
		InputSanityCheck.checkSameShape(derivX,derivY);
		if( derivX.stride != derivY.stride || derivX.startIndex != derivY.startIndex )
			throw new IllegalArgumentException("stride and start index must be the same");

		int width = widthSubregion*widthGrid;
		int radius = width/2;

		int X0 = radius,X1 = derivX.width-radius;
		int Y0 = radius,Y1 = derivX.height-radius;

		int numX = (int)((X1-X0)/periodColumns);
		int numY = (int)((Y1-Y0)/periodRows);

		imageDerivX.wrap(derivX);
		imageDerivY.wrap(derivY);

		descriptors.reset();

		for (int i = 0; i < numY; i++) {
			int y = (Y1-Y0)*i/(numY-1) + Y0;

			for (int j = 0; j < numX; j++) {
				int x = (X1-X0)*i/(numX-1) + X0;

				TupleDesc_F64 desc = descriptors.grow();

				computeDescriptor(x,y,desc);
			}
		}

	}