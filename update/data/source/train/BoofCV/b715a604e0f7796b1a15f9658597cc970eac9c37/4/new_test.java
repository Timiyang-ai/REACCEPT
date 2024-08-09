@Test
	public void process() {
		int widthCell = 8;

		for (int blockWidth = 1; blockWidth <= 3; blockWidth++) {
			for (int step = 1; step <= 3; step++) {

				DescribeDenseHogAlg<T, ?> alg = createAlg(10, widthCell, blockWidth, step);

				T input = imageType.createImage(width, height);
				GImageMiscOps.fillUniform(input, rand, 0, 120);

				alg.setInput(input);
				alg.process();

				FastQueue<Point2D_I32> points = alg.getLocations();
				FastQueue<TupleDesc_F64> descriptions = alg.getDescriptions();

				int pixelsBlock = blockWidth*widthCell;

				int index = 0;
				for (int y = 0; y+pixelsBlock <= height; y += widthCell*step) {
					for (int x = 0; x+pixelsBlock <= width; x += widthCell*step, index++) {
						assertEquals(points.get(index).x, x);
						assertEquals(points.get(index).y, y);
					}
				}

				assertEquals(index, points.size());
				assertEquals(index, descriptions.size());
			}
		}
	}