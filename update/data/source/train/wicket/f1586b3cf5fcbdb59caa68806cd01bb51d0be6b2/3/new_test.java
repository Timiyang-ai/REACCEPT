@Test
	public void acceptAbsolutePath()
	{
		SecurePackageResourceGuard guard = new SecurePackageResourceGuard();
		guard.addPattern("+*.gif");
		assertFalse(guard.accept("test.gif"));
		assertFalse(guard.accept("/test.gif"));
		assertTrue(guard.accept("mydir/test.gif"));
		assertTrue(guard.accept("/root/mydir/test.gif"));
	}