	@Test
	public void voidPatient_shouldVoidGivenPatientWithGivenReason() throws Exception {
		Patient patient = Context.getPatientService().getPatient(2);
		Patient voidedPatient = Context.getPatientService().voidPatient(patient, "Void for testing");
		
		Assert.assertTrue(voidedPatient.getVoided());
		Assert.assertEquals("Void for testing", voidedPatient.getVoidReason());
		Assert.assertFalse(Context.getPatientService().getAllPatients(false).contains(patient));
	}