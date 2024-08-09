public static String getMimeType (String fileName)
	{
		if (fileName == null || fileName.indexOf('.') < 0)
			return BINARY;
		//
		String extension = fileName.substring(fileName.lastIndexOf('.'));
		if (extension == null)
			return BINARY;
		
		for (int i = 0; i < TYPES.length; i++)
		{
			String[] type = TYPES[i];
			
			if (type[0].equals(extension.toLowerCase()))
				return type[1];
		}
		return BINARY;
	}