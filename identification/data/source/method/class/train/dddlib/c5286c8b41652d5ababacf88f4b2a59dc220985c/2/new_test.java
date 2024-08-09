@Test
	public void testGenerateConditionStatment() {
		String queryCondition = dynamicQueryCondition.generateConditionStatment().getStatment();
		assertEquals(" and name like ?", queryCondition);
		
		List<Object> values = new ArrayList<Object>();
		values.add("%test%");
		assertEquals(values, dynamicQueryCondition.generateConditionStatment().getValues());
		
		dynamicQueryCondition = this.createAndInitDynamicQueryConditionTwoValues();
		queryCondition = dynamicQueryCondition.generateConditionStatment().getStatment();
		assertEquals(" and age between ? and ?", queryCondition);
		
		values = new ArrayList<Object>();
		values.add("18");
		values.add("30");
		assertEquals(values, dynamicQueryCondition.generateConditionStatment().getValues());
	}