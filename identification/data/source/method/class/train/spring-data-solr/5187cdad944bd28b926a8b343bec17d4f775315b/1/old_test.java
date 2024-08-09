@Test
	public void testAnd() {
		Criteria criteria = new Criteria("field_1").startsWith("start").endsWith("end").and("field_2").startsWith("2start")
				.endsWith("2end");
		Assert.assertEquals("field_2", criteria.getField().getName());
		Assert.assertEquals(" AND ", criteria.getConjunctionOperator());
		Assert.assertEquals(2, criteria.getCriteriaChain().size());
	}