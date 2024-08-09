@Test
	public void accept()
	{
		PackageResourceGuard guard = new PackageResourceGuard();

		guard.setAllowAccessToRootResources(false);
		assertFalse(guard.accept(Integer.TYPE, "test.gif"));

		guard.setAllowAccessToRootResources(true);
		assertTrue(guard.accept(Integer.TYPE, "test.gif"));


	}