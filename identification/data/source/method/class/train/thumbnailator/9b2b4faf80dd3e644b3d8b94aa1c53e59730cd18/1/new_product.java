public void write(BufferedImage img) throws IOException
	{
		super.write(img);
		
		if (outputFormat == null)
		{
			throw new IllegalStateException("Output format has not been set.");
		}
		
		String formatName = outputFormat;
			
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
			else
			{
				List<String> supportedFormats = 
					ThumbnailatorUtils.getSupportedOutputFormatTypes(formatName);
				
				if (!supportedFormats.isEmpty())
				{
					writeParam.setCompressionType(supportedFormats.get(0));
				}
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
		
		ImageOutputStream ios = ImageIO.createImageOutputStream(os);
		
		if (ios == null)
		{
			throw new IOException("Could not open OutputStream.");
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
		
		/*
		 * Dispose the writer to free resources.
		 * 
		 * This seems to be the main culprit of `OutOfMemoryError`s which
		 * started to frequently appear with Java 7 Update 21.
		 * 
		 * Issue:
		 * http://code.google.com/p/thumbnailator/issues/detail?id=42
		 */
		writer.dispose();
		
		ios.close();
	}