	@Test
	public void retireConceptSource_shouldRetireConceptSource() {
		ConceptSource cs = conceptService.getConceptSource(3);
		conceptService.retireConceptSource(cs, "dummy reason for retirement");
		
		cs = conceptService.getConceptSource(3);
		Assert.assertTrue(cs.getRetired());
		Assert.assertEquals("dummy reason for retirement", cs.getRetireReason());
	}