	@Test
	public void getAsText_shouldPrintDateWithoutTime() throws ParseException {
		ed.setValue(ymdhm.parse("2011-10-27 00:00"));
		Assert.assertEquals("27/10/2011", ed.getAsText());
	}