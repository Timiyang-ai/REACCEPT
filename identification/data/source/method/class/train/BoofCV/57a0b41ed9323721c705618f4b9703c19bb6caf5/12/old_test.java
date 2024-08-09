@Test
	public void down_2inputs() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
		Class input[] = new Class[]{ImageUInt8.class, ImageUInt16.class,ImageFloat32.class, ImageFloat64.class};
		Class middle[] = new Class[]{ImageFloat32.class, ImageFloat32.class,ImageFloat32.class, ImageFloat64.class};

		for (int i = 0; i < input.length; i++) {
			ImageSingleBand in = GeneralizedImageOps.createSingleBand(input[i],17,14);
			ImageSingleBand mid = GeneralizedImageOps.createSingleBand(middle[i],3,14);
			ImageSingleBand found = GeneralizedImageOps.createSingleBand(input[i],3,4);
			ImageSingleBand expected = GeneralizedImageOps.createSingleBand(input[i],3,4);

			GImageMiscOps.fillUniform(in,rand,0,100);

			Method horizontal = ImplAverageDownSample.class.getDeclaredMethod("horizontal",input[i],middle[i]);
			Method vertical = BoofTesting.findMethod(ImplAverageDownSample.class,"vertical",middle[i],input[i]);

			horizontal.invoke(null,in,mid);
			vertical.invoke(null,mid,expected);

			AverageDownSampleOps.down(in,found);

			BoofTesting.assertEquals(expected,found,1e-4);
		}
	}