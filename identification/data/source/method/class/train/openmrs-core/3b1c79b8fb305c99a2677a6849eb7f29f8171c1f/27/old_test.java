	@Test
	public void getDrugsByIngredient_shouldReturnDrugsMatchedByDrugConcept() {
		List<Drug> drugs = conceptService.getDrugsByIngredient(new Concept(792));
		assertEquals(1, drugs.size());
	}