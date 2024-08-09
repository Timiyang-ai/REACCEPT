@Test
	public void accept()
	{
		SecurePackageResourceGuard guard = new SecurePackageResourceGuard();
		guard.setAllowAccessToWebInfResources(false);
		guard.addPattern("+*.gif");
		assertTrue(guard.accept(Application.class,
			Packages.absolutePath(Application.class, "test.gif")));
		assertTrue(guard.accept(Application.class,
			Packages.absolutePath(Application.class, "mydir/test.gif")));
		assertTrue(guard.accept(Application.class, "/root/mydir/test.gif"));
		assertTrue(guard.accept(Application.class,
			Packages.absolutePath(Application.class, "../test.gif")));
		assertTrue(guard.accept(Application.class,
			Packages.absolutePath(Application.class, "../../test.gif")));

		// web-inf (root package)
		assertFalse(guard.accept(Application.class,
			Packages.absolutePath(Application.class, "../../../test.gif")));
		guard.setAllowAccessToWebInfResources(true);
		assertTrue(guard.accept(Application.class,
			Packages.absolutePath(Application.class, "../../../test.gif")));

		boolean hit = false;
		try
		{
			// you can not go below root
			assertTrue(guard.accept(Application.class,
				Packages.absolutePath(Application.class, "../../../../test.gif")));
		}
		catch (IllegalArgumentException ex)
		{
			hit = true;
		}
		assertTrue("Expected an IllegalArgumentException", hit);
	}