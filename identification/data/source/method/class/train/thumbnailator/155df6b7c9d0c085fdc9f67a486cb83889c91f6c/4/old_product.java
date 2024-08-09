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
		
		/*
		 * Here, an explicit FileOutputStream is being created, as using a
		 * File object directly to obtain an ImageOutputStream was causing
		 * a problem where if the destination file already exists, then the
		 * image data was being written to the beginning of the file rather than
		 * creating a new file.
		 */
		ImageOutputStream ios;
		FileOutputStream fos;

		/*
		 * The following two lines used to be surrounded by a try-catch,
		 * but it has been removed, as the IOException which it was
		 * throwing in the catch block was not giving good feedback as to
		 * what was causing the original IOException.
		 * 
		 * It would have been informative to have the IOException which
		 * caused this problem, but the IOException in Java 5 does not
		 * have a "cause" parameter.
		 * 
		 * The "cause" parameter has been introduced in Java 6:
		 * http://docs.oracle.com/javase/6/docs/api/java/io/IOException.html#IOException%28java.lang.String,%20java.lang.Throwable%29
		 *
		 * TODO Whether to surround this portion of code in a try-catch
		 *      again is debatable, as it wouldn't really add more utility.
		 *
		 *      Furthermore, there are other calls in this method which will
		 *      throw IOExceptions, but they are not surrounded by try-catch
		 *      blocks. (A similar example exists in the OutputStreamImageSink
		 *      where the ImageIO.createImageOutputStream is not surrounded
		 *      in a try-catch.)
		 *
		 * Related issue:
		 * http://code.google.com/p/thumbnailator/issues/detail?id=37
		 */
		fos = new FileOutputStream(destinationFile);
		ios = ImageIO.createImageOutputStream(fos);
		
		if (ios == null || fos == null)
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
		fos.close();
	}