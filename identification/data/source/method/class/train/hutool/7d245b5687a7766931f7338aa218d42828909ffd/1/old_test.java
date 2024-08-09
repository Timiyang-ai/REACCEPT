	@Test
	public void toStringTest(){
		DateTime dateTime = new DateTime("2017-01-05 12:34:23", DatePattern.NORM_DATETIME_FORMAT);
		Assert.assertEquals("2017-01-05 12:34:23", dateTime.toString());
		
		String dateStr = dateTime.toString("yyyy/MM/dd");
		Assert.assertEquals("2017/01/05", dateStr);
	}