@Test
	public void testCreateThumbnail_FFII_Transcoding_Jpeg_Png() throws IOException
	{
		/*
		 * Actual test
		 */
		File inputFile = new File("test-resources/Thumbnailator/grid.jpg");
		File outputFile = File.createTempFile("thumbnailator-testing-", ".png");
		outputFile.deleteOnExit();
		
		Thumbnailator.createThumbnail(inputFile, outputFile, 50, 50);
		
		assertTrue(outputFile.exists());
		BufferedImage img = ImageIO.read(outputFile);
		assertEquals(
				"png",
				ImageIO.getImageReaders(
						ImageIO.createImageInputStream(outputFile)
				).next().getFormatName()
		);
		assertEquals(50, img.getWidth());
		assertEquals(50, img.getHeight());
	}