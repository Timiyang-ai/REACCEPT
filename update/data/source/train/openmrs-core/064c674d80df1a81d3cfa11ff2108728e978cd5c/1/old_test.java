@Test
	public void handle_shouldNotUnvoidTheOrdersAndEncountersThatNeverGotVoidedWithThePatient() {
		executeDataSet("org/openmrs/api/include/OrderServiceTest-otherEncounters.xml");
		Patient patient = Context.getPatientService().getPatient(7);
		
		EncounterService es = Context.getEncounterService();
		OrderService os = Context.getOrderService();
		
		Encounter testEncounter = es.getEncountersByPatient(patient).get(0);
		//santy checks
		Assert.assertFalse(testEncounter.getVoided());
		Assert.assertNull(testEncounter.getDateVoided());
		Assert.assertNull(testEncounter.getVoidedBy());
		Assert.assertNull(testEncounter.getVoidReason());
		
		//void one of the encounter orders be voided at a different time for testing purposes
		Assert.assertFalse(testEncounter.getOrders().isEmpty());
		Order testOrder = testEncounter.getOrders().iterator().next();
		Assert.assertFalse(testOrder.getVoided());
		Context.getOrderService().voidOrder(testOrder, "testing");
		Assert.assertTrue(testOrder.getVoided());
		TestUtil.waitForClockTick();
		
		//void one of the unvoided encounters for testing purposes
		es.voidEncounter(testEncounter, "random reason");
		Assert.assertTrue(testEncounter.getVoided());
		Assert.assertTrue(testOrder.getVoided());
		
		List<Patient> patients = new ArrayList<>();
		patients.add(patient);
		
		//wait a bit so that the patient isn't voided on the same millisecond
		TestUtil.waitForClockTick();
		
		//now void the patient for testing purposes
		patient = Context.getPatientService().voidPatient(patient, "Void Reason");
		Assert.assertTrue(patient.getVoided());
		new PatientDataUnvoidHandler().handle(patient, patient.getVoidedBy(), patient.getDateVoided(), null);
		//the encounter that was initially voided separately should still be voided
		testEncounter = es.getEncounter(testEncounter.getId());
		Assert.assertTrue(testEncounter.getVoided());
		Assert.assertNotNull(testEncounter.getDateVoided());
		Assert.assertNotNull(testEncounter.getVoidedBy());
		Assert.assertNotNull(testEncounter.getVoidReason());
		
		//the order that was initially voided separately should still be voided
		Assert.assertTrue(testOrder.getVoided());
		Assert.assertNotNull(testOrder.getDateVoided());
		Assert.assertNotNull(testOrder.getVoidedBy());
		Assert.assertNotNull(testOrder.getVoidReason());
		
	}