public static BufferedImage luminanceScale(final Image rawImg, final float percentage)
	{
		BufferedImage image = toARGB(rawImg);
		final int numComponents = image.getColorModel().getNumComponents();
		final float[] scales = new float[numComponents];
		final float[] offsets = new float[numComponents];

		Arrays.fill(offsets, 0f);
		for (int i = 0; i < numComponents; i++)
		{
			scales[i] = percentage;
		}
		// Set alpha to not scale
		scales[numComponents - 1] = 1f;

		return offset(image, scales, offsets);
	}