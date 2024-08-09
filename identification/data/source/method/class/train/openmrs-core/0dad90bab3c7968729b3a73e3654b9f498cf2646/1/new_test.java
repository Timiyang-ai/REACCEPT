@Test
	@Verifies(value = "should not copy over relationships that are only between the preferred and notpreferred patient", method = "mergePatients(Patient,Patient)")
	public void mergePatients_shouldNotCopyOverRelationshipsThatAreOnlyBetweenThePreferredAndNotpreferredPatient()
	        throws Exception {
		executeDataSet(PATIENT_RELATIONSHIPS_XML);
		
		Patient preferred = patientService.getPatient(999);
		Patient notPreferred = patientService.getPatient(2);
		voidOrders(Collections.singleton(notPreferred));
		
		patientService.mergePatients(preferred, notPreferred);
	}