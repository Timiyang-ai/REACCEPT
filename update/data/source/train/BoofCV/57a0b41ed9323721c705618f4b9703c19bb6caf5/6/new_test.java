@Test
	public void equalizeLocalNaive() {
		int numFound = 0;

		Method methods[] = ImplEnhanceHistogram.class.getMethods();
		for (Method method : methods) {
			if (method.getName().compareTo("equalizeLocalNaive") != 0)
				continue;

			numFound++;

			Class imageType = method.getParameterTypes()[0];
			GrayI input = (GrayI) GeneralizedImageOps.createSingleBand(imageType, width, height);
			GrayI output = (GrayI) GeneralizedImageOps.createSingleBand(imageType, width, height);

			equalizeLocalNaive(input, output);

			BoofTesting.checkSubImage(this, "equalizeLocalNaive", true, input, output);
		}

		assertEquals(2,numFound);
	}