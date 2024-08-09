public void process() {
		locations.reset();
		descriptions.reset();

		// see if the cell array needs to grow for this image.  Recycle data when growing
		growCellArray(derivX.width, derivX.height);

		computeCellHistograms();

		int cellRowMax = (cellRows - (widthBlock-1));
		int cellColMax = (cellCols - (widthBlock-1));

		for (int i = 0; i < cellRowMax; i += stepBlock) {
			for (int j = 0; j < cellColMax; j += stepBlock) {
				computeDescriptor(i,j);
			}
		}

	}