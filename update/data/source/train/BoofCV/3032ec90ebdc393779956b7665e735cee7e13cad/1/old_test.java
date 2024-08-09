@Test
	public void allInside_intrinsic() {

		// distorted pixel in original image
		float pixelX = 12.5f,pixelY = height-3;

		IntrinsicParameters orig = new IntrinsicParameters().
				fsetK(300, 320, 0, 150, 130, width, height).fsetRadial(0.1, 0.05);

		PointTransform_F32 distToNorm = LensDistortionOps.distortTransform(orig).undistort_F32(true, false);

		Point2D_F32 norm = new Point2D_F32();
		distToNorm.compute(pixelX, pixelY, norm);

		IntrinsicParameters adjusted = new IntrinsicParameters();
		PointTransform_F32 distToAdj = LensDistortionOps.allInside(orig,adjusted,false);

		Point2D_F32 adjPixel = new Point2D_F32();
		Point2D_F32 normFound = new Point2D_F32();
		distToAdj.compute(pixelX,pixelY,adjPixel);

		PerspectiveOps.convertPixelToNorm(adjusted, adjPixel, normFound);

		// see if the normalized image coordinates are the same
		assertEquals(norm.x, normFound.x, 1e-3);
		assertEquals(norm.y, normFound.y, 1e-3);
	}