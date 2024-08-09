	@Test
	public void setAsText_shouldHandleDate() throws ParseException {
		ed.setAsText("27/10/2011");
		Assert.assertEquals(ymdhm.parse("2011-10-27 00:00"), ed.getValue());
	}