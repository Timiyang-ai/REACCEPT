	@Test
	public void supports_shouldSupportObsClass() {
		assertTrue(obsValidator.supports(Obs.class));
		assertFalse(obsValidator.supports(Concept.class));
	}