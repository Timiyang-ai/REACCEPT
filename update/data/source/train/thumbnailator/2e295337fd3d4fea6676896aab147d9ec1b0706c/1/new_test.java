@Test
	public void build_OnlyScale()
	{
		ThumbnailParameter param = new ThumbnailParameterBuilder()
				.scale(0.5)
				.build();
		
		assertEquals(null, param.getSize());
		assertTrue(Double.compare(0.5, param.getWidthScalingFactor()) == 0);
		assertTrue(Double.compare(0.5, param.getHeightScalingFactor()) == 0);
		assertEquals(ThumbnailParameter.ORIGINAL_FORMAT, param.getOutputFormat());
		assertEquals(ThumbnailParameter.DEFAULT_FORMAT_TYPE, param.getOutputFormatType());
		assertTrue(Float.isNaN(param.getOutputQuality()));
		assertEquals(Resizers.PROGRESSIVE, param.getResizer());
		assertEquals(ThumbnailParameter.DEFAULT_IMAGE_TYPE, param.getType());
		assertEquals(Collections.emptyList(), param.getImageFilters());
	}