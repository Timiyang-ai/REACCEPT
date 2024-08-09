@Test
	public void testGenerateQuerySql() {
		String sql = pagingQuerier.generateQuerySql();
		assertEquals("select * from DATA_SOURCES limit 10 offset 0", sql);
		
		GeneralQuery generalQuery = new GeneralQuery("person");
		
		List<FieldDetail> fieldDetails = generalQuery.getFieldDetails();
		FieldDetail nameField = new FieldDetail("name");
		FieldDetail ageField = new FieldDetail("age");
		FieldDetail telField = new FieldDetail("tel");
		FieldDetail heightField = new FieldDetail("height");
		FieldDetail weightField = new FieldDetail("weight");
		
		fieldDetails.add(nameField);
		fieldDetails.add(ageField);
		fieldDetails.add(telField);
		fieldDetails.add(heightField);
		fieldDetails.add(weightField);
//		generalQuery.setFieldDetails(fieldDetails);
		
		List<PreQueryCondition> preQueryConditions = generalQuery.getPreQueryConditions();
		PreQueryCondition nameCondition = new PreQueryCondition("name");
		nameCondition.setValue("steven");
		nameCondition.setQueryOperation(QueryOperation.EQ);
		PreQueryCondition ageCondition = new PreQueryCondition("age");
		ageCondition.setValue("11");
		ageCondition.setQueryOperation(QueryOperation.GE);
		
		preQueryConditions.add(nameCondition);
		preQueryConditions.add(ageCondition);
//		generalQuery.setPreQueryConditions(preQueryConditions);
		
		List<DynamicQueryCondition> dynamicQueryConditions = generalQuery.getDynamicQueryConditions();
		DynamicQueryCondition telCondition = new DynamicQueryCondition("tel");
		telCondition.setQueryOperation(QueryOperation.LT);
		telCondition.setValue("1234567890");
		DynamicQueryCondition heightCondition = new DynamicQueryCondition("weight");
		heightCondition.setQueryOperation(QueryOperation.LE);
		heightCondition.setValue("120");
		
		dynamicQueryConditions.add(telCondition);
		dynamicQueryConditions.add(heightCondition);
//		generalQuery.setDynamicQueryConditions(dynamicQueryConditions);
		
//		DataSource dataSource = new DataSource();
//		dataSource.setConnectUrl("jdbc:mysql://localhost:3306/test");
//		dataSource.setJdbcDriver("com.mysql.jdbc.Driver");
//		dataSource.setUsername("root");
//		dataSource.setPassword("xmfang");
//		generalQuery.setDataSource(dataSource);
//
//		PagingQuerier pagingQuerier = new PagingQuerier(generalQuery.getQuerySql(), dataSource);
//		System.out.println(pagingQuerier.getTotalCount());
	}