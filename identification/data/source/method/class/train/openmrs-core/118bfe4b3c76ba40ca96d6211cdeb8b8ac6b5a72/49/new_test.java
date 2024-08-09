	@Test
	public void getConceptNameByUuid_shouldFindObjectGivenValidUuid() {
		String uuid = "9bc5693a-f558-40c9-8177-145a4b119ca7";
		ConceptName conceptName = Context.getConceptService().getConceptNameByUuid(uuid);
		Assert.assertEquals(1439, (int) conceptName.getConceptNameId());
	}