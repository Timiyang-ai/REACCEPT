	@Test
	public void setDefault() {
		RandomRegistry.reset();
		final Random devault = RandomRegistry.getRandom();
		Assert.assertNotNull(devault);

		RandomRegistry.setRandom(new Random());
		Assert.assertNotNull(RandomRegistry.getRandom());
		RandomRegistry.reset();

		assertSame(RandomRegistry.getRandom(), devault);
	}