@Test
	@Verifies(value = "should compare on id if its non null", method = "equals(Object)")
	public void equals_shouldCompareOnIdIfItsNonNull() throws Exception {
		ConceptDescription firstDesc = new ConceptDescription(1);
		ConceptDescription secondDesc = new ConceptDescription(1);
		Assert.assertTrue(firstDesc.equals(secondDesc));
	}