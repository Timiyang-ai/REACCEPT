@Test
	public void testCreateThumbnails_NoErrors() throws IOException
	{
		/*
		 * The files to make thumbnails of.
		 */
		List<File> files = Arrays.asList(
				new File("test-resources/Thumbnailator/grid.jpg"),
				new File("test-resources/Thumbnailator/grid.png")
		);
		
		/*
		 * Used to perform clean up.
		 */
		for (File f : files)
		{
			String fileName = f.getName();
			String newFileName = 
				Rename.PREFIX_DOT_THUMBNAIL.apply(fileName);
			
			new File(f.getParent(), newFileName).deleteOnExit();
		}
		
		Thumbnailator.createThumbnails(
				files,
				Rename.PREFIX_DOT_THUMBNAIL,
				50,
				50
		);
		
		/*
		 * Perform post-execution checks.
		 */
		BufferedImage img0 = 
			ImageIO.read(new File("test-resources/Thumbnailator/thumbnail.grid.jpg"));
		
		assertEquals(50, img0.getWidth());
		assertEquals(50, img0.getHeight());
		
		BufferedImage img1 = 
			ImageIO.read(new File("test-resources/Thumbnailator/thumbnail.grid.png"));
		
		assertEquals(50, img1.getWidth());
		assertEquals(50, img1.getHeight());
	}