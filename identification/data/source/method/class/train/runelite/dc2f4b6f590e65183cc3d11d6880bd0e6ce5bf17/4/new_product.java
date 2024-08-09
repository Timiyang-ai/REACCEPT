public static BufferedImage alphaOffset(final Image rawImg, final int offset)
	{
		BufferedImage image = toARGB(rawImg);
		final float offsetFloat = (float) offset;
		final int numComponents = image.getColorModel().getNumComponents();
		final float[] scales = new float[numComponents];
		final float[] offsets = new float[numComponents];

		Arrays.fill(scales, 1f);
		Arrays.fill(offsets, 0f);
		offsets[numComponents - 1] = offsetFloat;
		return offset(image, scales, offsets);
	}