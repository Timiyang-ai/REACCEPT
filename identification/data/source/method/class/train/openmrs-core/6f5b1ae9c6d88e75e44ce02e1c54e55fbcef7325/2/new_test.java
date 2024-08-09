	@Test
	@SuppressWarnings("deprecation")
	public void parseHL7Time_shouldHandle0615() throws HL7Exception {
		// set tz to be a __non DST__ timezone so this junit test works everywhere and always
		TimeZone originalTimeZone = TimeZone.getDefault();
		TimeZone.setDefault(TimeZone.getTimeZone("EAT"));
		
		Date parsedDate = HL7Util.parseHL7Time("0615");
		Assert.assertEquals(6, parsedDate.getHours());
		Assert.assertEquals(15, parsedDate.getMinutes());
		
		// reset the timezone
		TimeZone.setDefault(originalTimeZone);
	}