public static boolean remove(final java.io.File file)
	{
		if (file.exists() == false)
		{
			return false;
		}

		int retries = 10;

		boolean deleted = false;

		while ((deleted = file.delete()) == false && retries > 0)
		{
			retries--;
			try
			{
				Thread.sleep(100);
			}
			catch (InterruptedException ignored)
			{
			}
		}

		if (deleted == false && logger.isWarnEnabled())
		{
			logger.warn(
				"Cannot delete file '{}' for unknown reason. The file will be scheduled for deletion at JVM exit time.",
				file);
			file.deleteOnExit();
		}

		return deleted;
	}