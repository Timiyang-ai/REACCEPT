@Test
	public void getPatientIdentifiers_shouldNotGetVoidedPatientIdentifiers() throws Exception {
		
		List<PatientIdentifier> patientIdentifiers = dao.getPatientIdentifiers(null, new ArrayList<PatientIdentifierType>(),
		    new ArrayList<Location>(), new ArrayList<Patient>(), null);
		
		// standartTestDataset.xml contains 5 non-voided identifiers
		//
		// plus 1 non-voided identifier from HibernatePatientDAOTest-patients.xml
		
		Assert.assertEquals(8, patientIdentifiers.size());
		
		for (PatientIdentifier patientIdentifier : patientIdentifiers) {
			Assert.assertFalse(patientIdentifier.getVoided());
		}
	}