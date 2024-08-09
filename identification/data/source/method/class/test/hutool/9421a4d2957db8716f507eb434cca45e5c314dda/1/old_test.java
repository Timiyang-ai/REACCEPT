	@Test
	public void weekOfYearTest() {
		//第一周周日
		int weekOfYear1 = DateUtil.weekOfYear(DateUtil.parse("2016-01-03"));
		Assert.assertEquals(1, weekOfYear1);
		
		//第二周周四
		int weekOfYear2 = DateUtil.weekOfYear(DateUtil.parse("2016-01-07"));
		Assert.assertEquals(2, weekOfYear2);
	}