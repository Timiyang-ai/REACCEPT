@Test
	public void testOr() {
		Criteria criteria = new Criteria("field_1").startsWith("start").or("field_2").endsWith("end").startsWith("start2");
		Assert.assertEquals(" OR ", criteria.getConjunctionOperator());
		Assert.assertEquals(2, criteria.getCriteriaChain().size());
	}