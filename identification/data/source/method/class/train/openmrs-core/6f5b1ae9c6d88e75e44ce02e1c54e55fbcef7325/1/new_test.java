	@Test
	@SuppressWarnings("deprecation")
	public void parseHL7Timestamp_shouldNotFlubDstWith20091225123000() throws HL7Exception {
		// set tz to be US/Indianapolis so this junit test works everywhere and always
		TimeZone originalTimeZone = TimeZone.getDefault();
		TimeZone.setDefault(TimeZone.getTimeZone("EST"));
		
		Date d = HL7Util.parseHL7Date("20091225003000");
		Assert.assertEquals(25, d.getDate());
		
		// reset the timezone
		TimeZone.setDefault(originalTimeZone);
	}