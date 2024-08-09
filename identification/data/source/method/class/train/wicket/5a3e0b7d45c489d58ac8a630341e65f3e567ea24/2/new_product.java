public boolean accept(Class<?> scope, String absolutePath)
	{
		// path is already absolute
		return acceptAbsolutePath(absolutePath);
	}