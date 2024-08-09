	@Test
	public void parseTest() throws ParseException {
		//转换时间与SimpleDateFormat结果保持一致即可
		String time = "12:11:39";
		SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
		DateTime parse = DateUtil.parse("12:11:39");
		Assert.assertEquals(format.parse(time).getTime(), parse.getTime());
	}