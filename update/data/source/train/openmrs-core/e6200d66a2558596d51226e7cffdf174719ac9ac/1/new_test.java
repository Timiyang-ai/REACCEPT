@Test
	@Verifies(value = "should get visits by locations", method = "getVisits(Collection<VisitType>,Collection<Patient>,Collection<Location>,Collection<Concept>,Date,Date,Date,Date,boolean)")
	public void getVisits_shouldGetVisitsByLocations() throws Exception {
		List<Location> locations = new ArrayList<Location>();
		locations.add(new Location(1));
		Assert.assertEquals(1, Context.getVisitService().getVisits(null, null, locations, null, null, null, null, null,
		    null, true, false).size());
	}