	@Test
	public void getConceptByMapping_shouldGetConceptWithGivenCodeAndSourceHl7Code() {
		Concept concept = conceptService.getConceptByMapping("WGT234", "SSTRM");
		Assert.assertEquals(5089, concept.getId().intValue());
	}