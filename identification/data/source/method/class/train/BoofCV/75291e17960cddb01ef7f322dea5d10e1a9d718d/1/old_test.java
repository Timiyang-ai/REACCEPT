@Test
	public void scale() {
		ImageFloat32 input = new ImageFloat32(width,height);
		ImageFloat32 output = new ImageFloat32(width,height);
		ImageFloat32 output2 = new ImageFloat32(width,height);

		InterpolatePixel<ImageFloat32> interp = FactoryInterpolation.bilinearPixel(input);

		// check the two scale function
		DistortImageOps.scale(input,output,interp);
		DistortImageOps.scale(input,output2, TypeInterpolate.BILINEAR);

		// they should be identical
		BoofTesting.assertEquals(output,output2);

		interp.setImage(input);

		float scaleX = (float)input.width/(float)output.width;
		float scaleY = (float)input.height/(float)output.height;

		if( input.getTypeInfo().isInteger() ) {
			for( int i = 0; i < output.height; i++ ) {
				for( int j = 0; j < output.width; j++ ) {
					float val = interp.get(j*scaleX,i*scaleY);
					assertEquals((int)val,output.get(j,i),1e-4);
				}
			}
		} else {
			for( int i = 0; i < output.height; i++ ) {
				for( int j = 0; j < output.width; j++ ) {
					float val = interp.get(j*scaleX,i*scaleY);
					assertEquals(val,output.get(j,i),1e-4);
				}
			}
		}
	}