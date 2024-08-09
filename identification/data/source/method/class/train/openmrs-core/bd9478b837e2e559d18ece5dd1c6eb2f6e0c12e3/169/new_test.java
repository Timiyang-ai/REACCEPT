	@Test
	public void unvoidPatient_shouldUnvoidGivenPatient() throws Exception {
		Patient patient = Context.getPatientService().getPatient(2);
		
		Patient voidedPatient = Context.getPatientService().voidPatient(patient, "Void for testing");
		Assert.assertTrue(voidedPatient.getVoided());
		Assert.assertNotNull(voidedPatient.getVoidedBy());
		Assert.assertNotNull(voidedPatient.getVoidReason());
		Assert.assertNotNull(voidedPatient.getDateVoided());
		
		Patient unvoidedPatient = Context.getPatientService().unvoidPatient(voidedPatient);
		Assert.assertFalse(unvoidedPatient.getVoided());
		Assert.assertNull(unvoidedPatient.getVoidedBy());
		Assert.assertNull(unvoidedPatient.getVoidReason());
		Assert.assertNull(unvoidedPatient.getDateVoided());
	}