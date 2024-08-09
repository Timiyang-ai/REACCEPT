@Test
	public void testFuzzy() {
		Criteria criteria = new Criteria("field_1").fuzzy("value_1");
		assertCriteriaEntry(criteria.getCriteriaEntries(), 0, "$fuzzy#NaN", "value_1");
	}