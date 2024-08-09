	@Test
	public void endOfWeekTest() {
		DateTime endOfWeek = DateUtil.endOfWeek(DateUtil.date());
		Console.log(endOfWeek);
	}