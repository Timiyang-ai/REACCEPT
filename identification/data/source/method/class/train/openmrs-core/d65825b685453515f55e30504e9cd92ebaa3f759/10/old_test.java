	@Test
	public void isActive_shouldBeCurrentAfterDateActivated() throws Exception {
		//assertTrue("dateActivated==null && no end date should always be current", o.isActive(ymd.parse("2007-10-26")));
		o.setDateActivated(ymd.parse("2007-01-01"));

		assertFalse("shouldn't be current before dateActivated", o.isActive(ymd.parse("2006-10-26")));
		assertTrue("should be current after dateActivated", o.isActive(ymd.parse("2007-10-26")));
	}