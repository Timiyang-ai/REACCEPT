@Test
	@Verifies(value = "should get visits by indications", method = "getVisits(Collection<VisitType>,Collection<Patient>,Collection<Location>,Collection<Concept>,Date,Date,Date,Date,boolean)")
	public void getVisits_shouldGetVisitsByIndications() throws Exception {
		List<Concept> indications = new ArrayList<Concept>();
		indications.add(new Concept(5497));
		Assert.assertEquals(1, Context.getVisitService().getVisits(null, null, null, indications, null, null, null, null,
		    false).size());
	}