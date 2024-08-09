public synchronized void process( ImageFloat32 image, List<Point2D_F64> sides) {
		if( sides.size() != 4 )
			throw new IllegalArgumentException("Expected 4 sides");

		updateScore(image,sides);

		if( currentScore < bestScore ) {
			bestScore = currentScore;
			if( bestImage == null ) {
				bestImage = new BufferedImage(image.getWidth(), image.getHeight(),BufferedImage.TYPE_INT_RGB);
			}
			ConvertBufferedImage.convertTo(image,bestImage);
		}
	}