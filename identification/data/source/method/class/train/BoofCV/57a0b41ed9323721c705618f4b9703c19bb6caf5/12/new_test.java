@Test
	public void down_2inputs() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
		Class input[] = new Class[]{GrayU8.class, GrayU16.class,GrayF32.class, GrayF64.class};
		Class middle[] = new Class[]{GrayF32.class, GrayF32.class,GrayF32.class, GrayF64.class};

		for (int i = 0; i < input.length; i++) {
			ImageGray in = GeneralizedImageOps.createSingleBand(input[i],17,14);
			ImageGray mid = GeneralizedImageOps.createSingleBand(middle[i],3,14);
			ImageGray found = GeneralizedImageOps.createSingleBand(input[i],3,4);
			ImageGray expected = GeneralizedImageOps.createSingleBand(input[i],3,4);

			GImageMiscOps.fillUniform(in,rand,0,100);

			Method horizontal = ImplAverageDownSample.class.getDeclaredMethod("horizontal",input[i],middle[i]);
			Method vertical = BoofTesting.findMethod(ImplAverageDownSample.class,"vertical",middle[i],input[i]);

			horizontal.invoke(null,in,mid);
			vertical.invoke(null,mid,expected);

			AverageDownSampleOps.down(in,found);

			BoofTesting.assertEquals(expected,found,1e-4);
		}
	}