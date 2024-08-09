	@Test
	public void getDescriptions_shouldNotReturnNullIfDescriptionsListIsNull() {
		Concept c = new Concept();
		c.setDescriptions(null);
		Assert.assertTrue(c.getDescriptions().isEmpty());
		Assert.assertNotNull(c.getDescriptions());
	}