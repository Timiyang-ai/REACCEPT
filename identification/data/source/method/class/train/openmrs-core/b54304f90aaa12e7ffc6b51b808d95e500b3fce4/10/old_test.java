	@Test
	public void deserialize_shouldReconstructADateSerializedByThisHandler() throws ParseException {
		Date date = new SimpleDateFormat("yyyy-MM-dd").parse("2011-04-25");
		Assert.assertEquals(date, datatype.deserialize(datatype.serialize(date)));
	}