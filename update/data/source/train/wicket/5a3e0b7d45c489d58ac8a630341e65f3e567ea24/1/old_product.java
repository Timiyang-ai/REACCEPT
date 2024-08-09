@Override
	public boolean accept(Class<?> scope, String path)
	{
		String absolutePath = Packages.absolutePath(scope, path);
		return acceptAbsolutePath(absolutePath);
	}