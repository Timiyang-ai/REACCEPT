	@Test
	public void getDrugByUuid_shouldFindObjectGivenValidUuid() {
		String uuid = "3cfcf118-931c-46f7-8ff6-7b876f0d4202";
		Drug drug = Context.getConceptService().getDrugByUuid(uuid);
		Assert.assertEquals(2, (int) drug.getDrugId());
	}