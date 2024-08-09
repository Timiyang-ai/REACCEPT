@Test
	public void convertTo_SB() {
		Class[] types = new Class[]{GrayU8.class, GrayU16.class, GrayF32.class};

		for (Class t : types) {
			ImageBase image = GeneralizedImageOps.createSingleBand(t, imgWidth, imgHeight);

			GImageMiscOps.fillUniform(image, rand, 0, 100);
			BoofTesting.checkSubImage(this, "convertTo", false, image);
		}
	}