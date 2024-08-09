@Test
	public void testGenerateConditionStatment() {
		String queryCondition = dynamicQueryCondition.generateConditionStatment();
		assertEquals(" and name like '%test%'", queryCondition);
		
		dynamicQueryCondition = this.createAndInitDynamicQueryConditionTwoValues();
		queryCondition = dynamicQueryCondition.generateConditionStatment();
		assertEquals(" and age between '18' and '30'", queryCondition);
	}