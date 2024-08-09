@Test
	public void acceptAbsolutePath()
	{
		SecurePackageResourceGuard guard = new SecurePackageResourceGuard();
		guard.addPattern("+*.gif");
		assertFalse(guard.acceptAbsolutePath("test.gif"));
		assertFalse(guard.acceptAbsolutePath("/test.gif"));
		assertTrue(guard.acceptAbsolutePath("mydir/test.gif"));
		assertTrue(guard.acceptAbsolutePath("/root/mydir/test.gif"));
	}