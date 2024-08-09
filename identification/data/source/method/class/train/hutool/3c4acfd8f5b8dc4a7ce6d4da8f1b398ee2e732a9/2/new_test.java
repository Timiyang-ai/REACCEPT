	@Test
	public void offsetDateTest() {
		String dateStr = "2017-03-01 22:33:23";
		Date date = DateUtil.parse(dateStr);
		
		Date newDate = DateUtil.offset(date, DateField.DAY_OF_MONTH, 2);
		Assert.assertEquals("2017-03-03 22:33:23", newDate.toString());
		
		//常用偏移
		DateTime newDate2 = DateUtil.offsetDay(date, 3);
		Assert.assertEquals("2017-03-04 22:33:23", newDate2.toString());
		//常用偏移
		DateTime newDate3 = DateUtil.offsetHour(date, -3);
		Assert.assertEquals("2017-03-01 19:33:23", newDate3.toString());
	}