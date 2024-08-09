@Test
	public void subimage() {
		for( ImageType<T> t : imageTypes ) {
//			System.out.println("Image type "+t);

			ImageSuperpixels<T> alg = createAlg(t);

			T input = t.createImage(width,height);
			ImageSInt32 expected = new ImageSInt32(width,height);

			GImageMiscOps.fillUniform(input,rand,0,100);

			alg.segment(input,expected);

			// provide an output which is a sub-image
			ImageSInt32 found = new ImageSInt32(width+3,height+2).subimage(2,1,width+2,height+1,null);
			alg.segment(input,found);
			BoofTesting.assertEquals(expected,found,0);

			// Now make the input image an output
			input = BoofTesting.createSubImageOf(input);
			found = new ImageSInt32(width,height);

			alg.segment(input,found);
			BoofTesting.assertEquals(expected,found,0);
		}
	}