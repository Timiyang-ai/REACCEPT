	@Test
	public void betweenTest() {
		String dateStr1 = "2017-03-01 22:34:23";
		Date date1 = DateUtil.parse(dateStr1);
		
		String dateStr2 = "2017-04-01 23:56:14";
		Date date2 = DateUtil.parse(dateStr2);
		
		//相差月
		long betweenMonth = DateUtil.betweenMonth(date1, date2, false);
		Assert.assertEquals(1, betweenMonth);//相差一个月
		//反向
		betweenMonth = DateUtil.betweenMonth(date2, date1, false);
		Assert.assertEquals(1, betweenMonth);//相差一个月
		
		//相差天
		long betweenDay = DateUtil.between(date1, date2, DateUnit.DAY);
		Assert.assertEquals(31, betweenDay);//相差一个月，31天
		//反向
		betweenDay = DateUtil.between(date2, date1, DateUnit.DAY);
		Assert.assertEquals(31, betweenDay);//相差一个月，31天
		
		//相差毫秒
		long between = DateUtil.between(date1, date2, DateUnit.MS);
		String formatBetween = DateUtil.formatBetween(between, Level.MINUTE);
		Assert.assertEquals("31天1小时21分", formatBetween);
	}