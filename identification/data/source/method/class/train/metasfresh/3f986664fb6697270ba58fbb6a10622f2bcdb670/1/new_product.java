public static String getMimeType(final String fileName)
	{
		if (fileName == null || fileName.indexOf('.') < 0)
		{
			return TYPE_BINARY;
		}
		//
		final String extension = fileName.substring(fileName.lastIndexOf('.'));
		if (extension == null)
		{
			return TYPE_BINARY;
		}

		final String extensionLC = extension.toLowerCase();
		final String type = fileExtension2mimeType.get(extensionLC);
		return type == null ? TYPE_BINARY : type;
	}