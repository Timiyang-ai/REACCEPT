@Test
	public void convertTo_SB() {
		Class[] types = new Class[]{ImageUInt8.class, ImageUInt16.class, ImageFloat32.class};

		for (Class t : types) {
			ImageBase image = GeneralizedImageOps.createSingleBand(t, imgWidth, imgHeight);

			GImageMiscOps.fillUniform(image, rand, 0, 100);
			BoofTesting.checkSubImage(this, "convertTo", false, image);
		}
	}