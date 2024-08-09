@Test
	@Verifies(value = "should get visits by indications", method = "getVisits(Collection<VisitType>,Collection<Patient>,Collection<Location>,Collection<Concept>,Date,Date,Date,Date,boolean)")
	public void getVisits_shouldGetVisitsByIndications() throws Exception {
		Assert.assertEquals(1, Context.getVisitService().getVisits(null, null, null,
		    Collections.singletonList(new Concept(5497)), null, null, null, null, false).size());
	}