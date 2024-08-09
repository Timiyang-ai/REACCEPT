@Test
	public void accept()
	{
		PackageResourceGuard guard = new PackageResourceGuard();

		guard.setAllowAccessToRootResources(false);
		assertFalse(guard.accept("test.gif"));

		guard.setAllowAccessToRootResources(true);
		assertTrue(guard.accept("test.gif"));


	}