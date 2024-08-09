	@Test
	public void getTimeZoneOffset_shouldReturnTimezoneForGivenDateAndNotTheCurrentDate() throws ParseException {
		// set tz to be US/Indianapolis so this junit test works everywhere and always
		TimeZone originalTimeZone = TimeZone.getDefault();
		TimeZone.setDefault(TimeZone.getTimeZone("GMT-05:00"));
		
		Assert.assertEquals("-0500", HL7Util.getTimeZoneOffset("197804110615", new SimpleDateFormat("yyyyMMdd")
		        .parse("20091225")));
		
		// reset the timezone
		TimeZone.setDefault(originalTimeZone);
	}