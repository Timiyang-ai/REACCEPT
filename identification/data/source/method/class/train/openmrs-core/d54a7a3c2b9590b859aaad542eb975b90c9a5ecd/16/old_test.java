	@Test
	public void getVisits_shouldGetVisitsByIndications() {
		assertEquals(1, visitService.getVisits(null, null, null, Collections.singletonList(new Concept(5497)),
		    null, null, null, null, null, true, false).size());
	}