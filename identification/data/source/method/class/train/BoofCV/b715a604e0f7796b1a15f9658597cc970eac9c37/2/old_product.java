public void process() {
		locations.reset();
		descriptions.reset();

		// see if the cell array needs to grow for this image.  Recycle data when growing
		growCellArray(derivX.width, derivX.height);

		computeCellHistograms();

		int cellRowMax = cellRows - widthBlock;
		int cellColMax = cellCols - widthBlock;

		for (int i = 0; i < cellRowMax; i++) {
			for (int j = 0; j < cellColMax; j++) {
				computeDescriptor(i,j);
			}
		}

	}