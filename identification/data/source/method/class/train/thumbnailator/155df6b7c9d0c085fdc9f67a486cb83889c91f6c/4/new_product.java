public void write(BufferedImage img) throws IOException
	{
		/*
		 * Add or replace the file extension of the output file.
		 * 
		 * If the file extension matches the output format's extension,
		 * then leave as is.
		 * 
		 * Else, append the extension for the output format to the filename.
		 */
		String fileExtension = getExtension(destinationFile);
		
		String formatName = outputFormat;
		if (formatName != null && (fileExtension == null || !isMatchingFormat(formatName, fileExtension)))
		{
			destinationFile = new File(destinationFile.getAbsolutePath() + "." + formatName);
		}
		
		if (!allowOverwrite && destinationFile.exists()) {
			throw new IllegalArgumentException("The destination file exists.");
		}
		
		/*
		 * If a formatName is not specified, then attempt to determine it from
		 * the file extension.
		 */
		if (formatName == null && fileExtension != null)
		{
			Iterator<ImageReader> rIter = ImageIO.getImageReadersBySuffix(fileExtension);
			
			if (rIter.hasNext())
			{
				formatName = rIter.next().getFormatName();
			}
		}
		
		if (formatName == null)
		{
			throw new UnsupportedFormatException(
					formatName,
					"Could not determine output format."
			);
		}
		
		imageSink = new OutputStreamImageSink(new FileOutputStream(destinationFile));
		imageSink.setThumbnailParameter(param);
		imageSink.setOutputFormatName(formatName);
		
		imageSink.write(img);
	}