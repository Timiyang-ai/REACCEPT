protected boolean acceptAbsolutePath(String path)
	{
		int ixExtension = path.lastIndexOf('.');
		int len = path.length();
		final String ext;
		if (ixExtension <= 0 || ixExtension == len ||
			(path.lastIndexOf(File.separator) + 1) == ixExtension)
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

		String filename = Strings.lastPathComponent(path, File.separatorChar);
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

		if (!allowAccessToRootResources)
		{
			String absolute = path;
			if ("\\".equals(File.separator))
			{
				// handle a windows path which may have a drive letter in it

				if (absolute.indexOf(":\\") > 0)
				{
					// strip the drive letter off the path
					absolute = absolute.substring(absolute.indexOf(":\\") + 2);
				}
			}

			if (absolute.startsWith(File.separator))
			{
				absolute = absolute.substring(1);
			}
			if (!absolute.contains(File.separator))
			{
				log.warn("Access to root directory is by default disabled for shared resources: " +
					path);
				return false;
			}
		}

		return true;
	}