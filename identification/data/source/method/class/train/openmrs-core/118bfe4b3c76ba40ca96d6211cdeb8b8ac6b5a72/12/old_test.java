	@Test
	public void getConceptSourceByUuid_shouldFindObjectGivenValidUuid() {
		String uuid = "75f5b378-5065-11de-80cb-001e378eb67e";
		ConceptSource conceptSource = Context.getConceptService().getConceptSourceByUuid(uuid);
		Assert.assertEquals(3, (int) conceptSource.getConceptSourceId());
	}