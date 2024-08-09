public void write(BufferedImage img) throws IOException
	{
		super.write(img);
		
		/* TODO refactor.
		 * The following code has been adapted from the 
		 * StreamThumbnailTask.write method.
		 */
		
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
		
		// Checks for available writers for the format.
		Iterator<ImageWriter> writers = 
			ImageIO.getImageWritersByFormatName(formatName);
		
		if (!writers.hasNext())
		{
			throw new UnsupportedFormatException(
					formatName, 
					"No suitable ImageWriter found for " + formatName + "."
			);
		}
		
		ImageWriter writer = writers.next();
		
		ImageWriteParam writeParam = writer.getDefaultWriteParam();
		if (writeParam.canWriteCompressed() && param != null)
		{
			writeParam.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
			
			/*
			 * Sets the compression format type, if specified.
			 * 
			 * Note:
			 * The value to denote that the codec's default compression type
			 * should be used is null. 
			 */
			if (param.getOutputFormatType() != ThumbnailParameter.DEFAULT_FORMAT_TYPE)
			{
				writeParam.setCompressionType(param.getOutputFormatType());
			}
			
			/*
			 * Sets the compression quality, if specified.
			 * 
			 * Note:
			 * The value to denote that the codec's default compression quality
			 * should be used is Float.NaN. 
			 */
			if (!Float.isNaN(param.getOutputQuality()))
			{
				writeParam.setCompressionQuality(param.getOutputQuality());
			}
		}
		
		ImageOutputStream ios = 
			ImageIO.createImageOutputStream(destinationFile);
		
		if (ios == null)
		{
			throw new IOException("Could not open output file.");
		}
		
		/*
		 * Note:
		 * The following code is a workaround for the JPEG writer which ships
		 * with the JDK.
		 * 
		 * At issue is, that the JPEG writer appears to write the alpha
		 * channel when it should not. To circumvent this, images which are
		 * to be saved as a JPEG will be copied to another BufferedImage without
		 * an alpha channel before it is saved.
		 * 
		 * Also, the BMP writer appears not to support ARGB, so an RGB image
		 * will be produced before saving.
		 */
		if (
				formatName.equalsIgnoreCase("jpg")
				|| formatName.equalsIgnoreCase("jpeg")
				|| formatName.equalsIgnoreCase("bmp")
		)
		{
			img = BufferedImages.copy(img, BufferedImage.TYPE_INT_RGB);
		}
		
		writer.setOutput(ios);
		writer.write(null, new IIOImage(img, null, null), writeParam);
		
		ios.close();
	}