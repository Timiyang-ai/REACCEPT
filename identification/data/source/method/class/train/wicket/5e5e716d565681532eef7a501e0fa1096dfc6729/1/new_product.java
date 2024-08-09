public static boolean remove(final java.io.File file)
	{
		if (file != null && file.isFile())
		{
			for (int j = 0; j < 5; ++j)
			{
				for (int i = 0; i < 10; ++i)
				{
					if (file.delete())
					{
						return true;
					}

					try
					{
						Thread.sleep(100);
					}
					catch (InterruptedException ix)
					{
						Thread.currentThread().interrupt();
					}
				}
			}
		}

		return false;
	}