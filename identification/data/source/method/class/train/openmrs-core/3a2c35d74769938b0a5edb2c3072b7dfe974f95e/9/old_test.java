	@Test
	public void getDrugs_shouldReturnDrugsThatAreNotRetired() {
		List<Drug> drugs = Context.getConceptService().getDrugs("ASPIRIN" /* is not retired */);
		Assert.assertFalse(drugs.get(0).getRetired());
	}