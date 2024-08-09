@Test
	public void acceptAbsolutePath()
	{
		PackageResourceGuard guard = new PackageResourceGuard();
		guard.setAllowAccessToRootResources(false);

		assertTrue(guard.acceptAbsolutePath("/test/test.js"));
		assertTrue(guard.acceptAbsolutePath("/test/.java"));
		assertFalse(guard.acceptAbsolutePath("/test.js"));

		if ("\\".equals(File.separator))
		{
			assertTrue(guard.acceptAbsolutePath("c:\\test\\org\\apache\\.java"));
			assertTrue(guard.acceptAbsolutePath("\\test\\org\\apache\\.java"));
			assertTrue(guard.acceptAbsolutePath("c:\\test\\org\\apache\\test.js"));
			assertTrue(guard.acceptAbsolutePath("\\test\\org\\apache\\test.js"));
			assertFalse(guard.acceptAbsolutePath("c:\\test.js"));
			assertFalse(guard.acceptAbsolutePath("\\test.js"));

			// java also generates file paths with '/' on windows
			assertTrue(guard.acceptAbsolutePath("c:/test/org/apache/.java"));
			assertTrue(guard.acceptAbsolutePath("/test/org/apache/.java"));
			assertTrue(guard.acceptAbsolutePath("c:/test/org/apache/test.js"));
			assertTrue(guard.acceptAbsolutePath("/test/org/apache/test.js"));
			assertFalse(guard.acceptAbsolutePath("c:/test.js"));
			assertFalse(guard.acceptAbsolutePath("/test.js"));
		}
	}