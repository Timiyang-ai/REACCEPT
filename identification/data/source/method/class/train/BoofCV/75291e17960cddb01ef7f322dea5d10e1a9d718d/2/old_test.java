@Test
	public void rotate() {
		ImageFloat32 input = new ImageFloat32(width,height);
		ImageFloat32 output = new ImageFloat32(width,height);
		ImageFloat32 output2 = new ImageFloat32(width,height);

		InterpolatePixel<ImageFloat32> interp = FactoryInterpolation.bilinearPixel(input);

		int middleX = input.width/2;
		int middleY = input.height/2;

		DistortImageOps.rotate(input,output,interp,middleX,middleY,(float)Math.PI/2f);
		DistortImageOps.rotate(input,output2,TypeInterpolate.BILINEAR,middleX,middleY,(float)Math.PI/2f);

		// they should be identical
		BoofTesting.assertEquals(output,output2);

		// check for a 90 degrees rotation
		assertEquals(input.get(middleX+5,middleY+3),output.get(middleX+3,middleY+5),1e-4);
		assertEquals(input.get(middleX+4,middleY+6),output.get(middleX+6,middleY+4),1e-4);
	}