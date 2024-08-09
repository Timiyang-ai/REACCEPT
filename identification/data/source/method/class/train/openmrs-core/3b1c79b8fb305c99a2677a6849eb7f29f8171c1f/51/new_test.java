	@Test
	public void getConceptMapTypeByUuid_shouldReturnAConceptMapTypeMatchingTheSpecifiedUuid() {
		Assert.assertEquals("is-parent-to", Context.getConceptService().getConceptMapTypeByUuid(
		    "0e7a8536-49d6-11e0-8fed-18a905e044dc").getName());
	}