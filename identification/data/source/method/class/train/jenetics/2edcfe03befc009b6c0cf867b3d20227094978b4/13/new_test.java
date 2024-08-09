	@Test
	public void setRandom() {
		final Random random = new Random();
		RandomRegistry.setRandom(random);

		assertSame(RandomRegistry.getRandom(), random);
	}