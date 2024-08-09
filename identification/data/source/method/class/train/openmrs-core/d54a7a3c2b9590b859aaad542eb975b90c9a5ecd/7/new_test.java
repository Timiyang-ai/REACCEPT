	@Test
	public void getVisitsByPatient_shouldReturnEmptyListGivenNull() {
		
		assertThat(visitService.getVisitsByPatient(null), is(empty()));
	}