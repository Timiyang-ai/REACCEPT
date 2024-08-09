public static void createThumbnails(
			Collection<? extends File> files,
			Rename rename,
			int width,
			int height
	) 
	throws IOException
	{
		validateDimensions(width, height);
		
		if (files == null)
		{
			throw new NullPointerException("Collection of Files is null.");
		}
		if (rename == null)
		{
			throw new NullPointerException("Rename is null.");
		}
		
		ThumbnailParameter param = 
			new ThumbnailParameterBuilder()
				.size(width, height)
				.build();
		
		for (File inFile : files)
		{
			File outFile = 
				new File(inFile.getParent(), rename.apply(inFile.getName(), param));
			
			createThumbnail(inFile, outFile, width, height);
		}
	}