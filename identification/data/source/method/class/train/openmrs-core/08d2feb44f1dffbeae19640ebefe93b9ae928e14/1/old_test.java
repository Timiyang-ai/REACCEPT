	@Test
	public void isDiscontinued_shouldNotDiscontinueGivenOrderWithoutDates() throws Exception {
		assertFalse("order without dates shouldn't be discontinued", o.isDiscontinued(ymd.parse("2007-10-26")));
	}