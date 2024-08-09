@Test
	public void testFuzzy() {
		Criteria criteria = new Criteria("field_1").fuzzy("value_1");
		CriteriaEntry entry = getCriteriaEntryByPosition(criteria.getCriteriaEntries(), 0);
		Assert.assertEquals("value_1", ((Object[]) entry.getValue())[0]);
		Assert.assertEquals(Float.NaN, ((Object[]) entry.getValue())[1]);
	}