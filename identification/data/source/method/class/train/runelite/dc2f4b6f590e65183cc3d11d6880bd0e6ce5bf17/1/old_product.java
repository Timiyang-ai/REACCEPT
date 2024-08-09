public static BufferedImage alphaOffset(final BufferedImage image, final float percentage)
	{
		final int numComponents = image.getColorModel().getNumComponents();
		final float[] scales = new float[numComponents];
		final float[] offsets = new float[numComponents];

		Arrays.fill(scales, 1f);
		Arrays.fill(offsets, 0f);
		scales[numComponents - 1] = percentage;
		return offset(image, scales, offsets);
	}