	@Test
	public void getConceptDescriptionByUuid_shouldFindObjectGivenValidUuid() {
		String uuid = "5f4d710b-d333-40b7-b449-6e0e739d15d0";
		ConceptDescription conceptDescription = Context.getConceptService().getConceptDescriptionByUuid(uuid);
		Assert.assertEquals(1, (int) conceptDescription.getConceptDescriptionId());
	}