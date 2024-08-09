@Test
	@Verifies(value = "should not unvoid the orders and encounters that never got voided with the patient", method = "handle(Patient,User,Date,String)")
	public void handle_shouldNotUnvoidTheOrdersAndEncountersThatNeverGotVoidedWithThePatient() throws Exception {
		Patient patient = Context.getPatientService().getPatient(7);
		
		EncounterService es = Context.getEncounterService();
		OrderService os = Context.getOrderService();
		
		//void one of the unvoided encounters for testing purposes
		Encounter testEncounter = es.getEncountersByPatient(patient).get(0);
		//santy checks
		Assert.assertNotNull(testEncounter);
		Assert.assertNull(testEncounter.getDateVoided());
		Assert.assertNull(testEncounter.getVoidedBy());
		Assert.assertNull(testEncounter.getVoidReason());
		
		es.voidEncounter(testEncounter, "random reason");
		Assert.assertTrue(testEncounter.isVoided());
		
		//void one of the unvoided orders for testing purposes
		//TODO To be fixed in order entry sprint
		/*Order testOrder = os.getOrdersByPatient(patient).get(0);
		Assert.assertNotNull(testOrder);
		Assert.assertNull(testOrder.getDateVoided());
		Assert.assertNull(testOrder.getVoidedBy());
		Assert.assertNull(testOrder.getVoidReason());
		
		os.voidOrder(testOrder, "random reason");
		Assert.assertTrue(testOrder.isVoided());
		
		//wait a bit so that the patient isn't voided on the same millisecond
		TestUtil.waitForClockTick();
		
		//now void the patient for testing purposes
		patient = Context.getPatientService().voidPatient(patient, "Void Reason");
		Assert.assertTrue(patient.isVoided());
		new PatientDataUnvoidHandler().handle(patient, patient.getVoidedBy(), patient.getDateVoided(), null);
		//the encounter that was initially voided separately should still be voided
		testEncounter = es.getEncounter(testEncounter.getId());
		Assert.assertTrue(testEncounter.isVoided());
		Assert.assertNotNull(testEncounter.getDateVoided());
		Assert.assertNotNull(testEncounter.getVoidedBy());
		Assert.assertNotNull(testEncounter.getVoidReason());
		
		//the order that was initially voided separately should still be voided
		Assert.assertTrue(testOrder.isVoided());
		Assert.assertNotNull(testOrder.getDateVoided());
		Assert.assertNotNull(testOrder.getVoidedBy());
		Assert.assertNotNull(testOrder.getVoidReason());*/

	}