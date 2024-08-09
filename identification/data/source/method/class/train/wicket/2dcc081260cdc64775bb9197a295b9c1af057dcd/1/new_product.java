protected boolean acceptAbsolutePath(String path)
	{
		int ixExtension = path.lastIndexOf('.');
		int len = path.length();
		final String ext;
		if (ixExtension <= 0 || ixExtension == len || (path.lastIndexOf('/') + 1) == ixExtension)
		{
			ext = null;
		}
		else
		{
			ext = path.substring(ixExtension + 1).toLowerCase().trim();
		}

		if ("html".equals(ext) &&
			getClass().getClassLoader().getResource(path.replaceAll("\\.html", ".class")) != null)
		{
			log.warn("Access denied to shared (static) resource because it is a Wicket markup file: " +
				path);
			return false;
		}

		if (acceptExtension(ext) == false)
		{
			log.warn("Access denied to shared (static) resource because of the file extension: " +
				path);
			return false;
		}

		String filename = Strings.lastPathComponent(path, '/');
		if (acceptFile(filename) == false)
		{
			log.warn("Access denied to shared (static) resource because of the file name: " + path);
			return false;
		}

		// Only if a placeholder, e.g. $up$ is defined, access to parent directories is allowed
		if (Strings.isEmpty(Application.get().getResourceSettings().getParentFolderPlaceholder()))
		{
			if (path.contains(".."))
			{
				log.warn("Access to parent directories via '..' is by default disabled for shared resources: " +
					path);
				return false;
			}
		}

		return true;
	}