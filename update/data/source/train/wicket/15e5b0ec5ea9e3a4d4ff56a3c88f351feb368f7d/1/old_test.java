@Test
	public void acceptAbsolutePath()
	{
		PackageResourceGuard guard = new PackageResourceGuard();
		guard.setAllowAccessToRootResources(false);

		assertTrue(guard.acceptAbsolutePath("/test/test.js"));
		assertFalse(guard.acceptAbsolutePath("/test.js"));

		if ("\\".equals(File.pathSeparator))
		{
			assertTrue(guard.acceptAbsolutePath("c:\\test\\org\\apache\\test.js"));
			assertTrue(guard.acceptAbsolutePath("\\test\\org\\apache\\test.js"));
			assertFalse(guard.acceptAbsolutePath("c:\\test.js"));
			assertFalse(guard.acceptAbsolutePath("\\test.js"));
		}

	}