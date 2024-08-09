public void fit( List<Point2D_I32> contour , GrowQueue_I32 corners ,
						Quadrilateral_F64 output )
	{
		// pick the 4 largest segments to act as the initial seeds
		segments.reset();
		for (int i = 0; i < corners.size; i++) {
			int next = (i+1)%corners.size;
			segments.grow().set(corners.get(i),corners.get(next),contour.size());
		}
		sorterLength.sort(segments.data, segments.size);

		// order the lines so that they can be converted into a quad later on easily
		// bubble sort below
		bubbleSortLines(segments);

		// now create the lines
		for (int i = 0; i < 4; i++) {
			createLine(segments.get(i), contour, lines[i]);
		}

		// estimate line equations
		performLineEM(contour);

		// convert from lines to quadrilateral
		convert(lines,output);
	}