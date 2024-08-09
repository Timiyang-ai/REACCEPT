public static BufferedImage grayscaleOffset(final BufferedImage image, final int offset)
	{
		final float offsetFloat = (float) offset;
		final int numComponents = image.getColorModel().getNumComponents();
		final float[] scales = new float[numComponents];
		final float[] offsets = new float[numComponents];

		Arrays.fill(scales, 1f);
		for (int i = 0; i < numComponents; i++)
		{
			offsets[i] = offsetFloat;
		}
		// Set alpha to not offset
		offsets[numComponents - 1] = 0f;

		return offset(image, scales, offsets);
	}