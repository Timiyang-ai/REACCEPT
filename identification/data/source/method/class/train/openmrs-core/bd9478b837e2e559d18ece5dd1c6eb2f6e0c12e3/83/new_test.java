	@Test(expected = IllegalArgumentException.class)
	public void getEncounter_shouldThrowErrorIfGivenNullParameter() {
		Context.getEncounterService().getEncounter(null);
	}