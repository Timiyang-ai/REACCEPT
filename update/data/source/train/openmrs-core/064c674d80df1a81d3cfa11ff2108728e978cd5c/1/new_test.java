@Test
	public void handle_shouldNotUnvoidTheOrdersAndEncountersThatNeverGotVoidedWithThePatient() {
		executeDataSet("org/openmrs/api/include/OrderServiceTest-otherEncounters.xml");
		Patient patient = Context.getPatientService().getPatient(7);
		
		EncounterService es = Context.getEncounterService();
		OrderService os = Context.getOrderService();
		
		Encounter testEncounter = es.getEncountersByPatient(patient).get(0);
		//santy checks
		assertFalse(testEncounter.getVoided());
		assertNull(testEncounter.getDateVoided());
		assertNull(testEncounter.getVoidedBy());
		assertNull(testEncounter.getVoidReason());
		
		//void one of the encounter orders be voided at a different time for testing purposes
		assertFalse(testEncounter.getOrders().isEmpty());
		Order testOrder = testEncounter.getOrders().iterator().next();
		assertFalse(testOrder.getVoided());
		Context.getOrderService().voidOrder(testOrder, "testing");
		assertTrue(testOrder.getVoided());
		TestUtil.waitForClockTick();
		
		//void one of the unvoided encounters for testing purposes
		es.voidEncounter(testEncounter, "random reason");
		assertTrue(testEncounter.getVoided());
		assertTrue(testOrder.getVoided());
		
		List<Patient> patients = new ArrayList<>();
		patients.add(patient);
		
		//wait a bit so that the patient isn't voided on the same millisecond
		TestUtil.waitForClockTick();
		
		//now void the patient for testing purposes
		patient = Context.getPatientService().voidPatient(patient, "Void Reason");
		assertTrue(patient.getVoided());
		new PatientDataUnvoidHandler().handle(patient, patient.getVoidedBy(), patient.getDateVoided(), null);
		//the encounter that was initially voided separately should still be voided
		testEncounter = es.getEncounter(testEncounter.getId());
		assertTrue(testEncounter.getVoided());
		assertNotNull(testEncounter.getDateVoided());
		assertNotNull(testEncounter.getVoidedBy());
		assertNotNull(testEncounter.getVoidReason());
		
		//the order that was initially voided separately should still be voided
		assertTrue(testOrder.getVoided());
		assertNotNull(testOrder.getDateVoided());
		assertNotNull(testOrder.getVoidedBy());
		assertNotNull(testOrder.getVoidReason());
		
	}