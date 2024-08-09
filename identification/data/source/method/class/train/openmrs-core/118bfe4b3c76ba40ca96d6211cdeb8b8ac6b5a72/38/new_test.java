	@Test
	public void getPatientByUuid_shouldFindObjectGivenValidUuid() throws Exception {
		String uuid = "da7f524f-27ce-4bb2-86d6-6d1d05312bd5";
		Patient patient = Context.getPatientService().getPatientByUuid(uuid);
		Assert.assertEquals(2, (int) patient.getPatientId());
	}