@Test
	@Ignore
	@Verifies(value = "should void the orders encounters and observations associated with the patient", method = "handle(Patient,User,Date,String)")
	public void handle_shouldVoidTheOrdersEncountersAndObservationsAssociatedWithThePatient() throws Exception {
		Patient patient = Context.getPatientService().getPatient(7);
		Assert.assertFalse(patient.isVoided());
		
		List<Encounter> encounters = Context.getEncounterService().getEncountersByPatient(patient);
		List<Obs> observations = Context.getObsService().getObservationsByPerson(patient);
		List<Order> orders = Context.getOrderService().getAllOrdersByPatient(patient);
		
		//we should have some unvoided encounters, obs and orders for the test to be concrete
		Assert.assertTrue(CollectionUtils.isNotEmpty(encounters));
		Assert.assertTrue(CollectionUtils.isNotEmpty(observations));
		Assert.assertTrue(CollectionUtils.isNotEmpty(orders));
		
		//check that fields to be set by the handler are initially null 
		for (Encounter encounter : encounters) {
			Assert.assertNull(encounter.getDateVoided());
			Assert.assertNull(encounter.getVoidedBy());
			Assert.assertNull(encounter.getVoidReason());
		}
		for (Obs obs : observations) {
			Assert.assertNull(obs.getDateVoided());
			Assert.assertNull(obs.getVoidedBy());
			Assert.assertNull(obs.getVoidReason());
		}
		for (Order order : orders) {
			Assert.assertNull(order.getDateVoided());
			Assert.assertNull(order.getVoidedBy());
			Assert.assertNull(order.getVoidReason());
		}
		
		new PatientDataVoidHandler().handle(patient, new User(1), new Date(), "voidReason");
		
		//all encounters void related fields should have been set
		for (Encounter encounter : encounters) {
			Assert.assertTrue(encounter.isVoided());
			Assert.assertNotNull(encounter.getDateVoided());
			Assert.assertNotNull(encounter.getVoidedBy());
			Assert.assertNotNull(encounter.getVoidReason());
		}
		//all obs void related fields should have been set
		for (Obs obs : observations) {
			Assert.assertTrue(obs.isVoided());
			Assert.assertNotNull(obs.getDateVoided());
			Assert.assertNotNull(obs.getVoidedBy());
			Assert.assertNotNull(obs.getVoidReason());
		}
		//all order void related fields should have been set
		for (Order order : orders) {
			Assert.assertTrue(order.isVoided());
			Assert.assertNotNull(order.getDateVoided());
			Assert.assertNotNull(order.getVoidedBy());
			Assert.assertNotNull(order.getVoidReason());
		}
		
		//refresh the lists and check that all encounters, obs and orders were voided
		encounters = Context.getEncounterService().getEncountersByPatient(patient);
		observations = Context.getObsService().getObservationsByPerson(patient);
		orders = Context.getOrderService().getAllOrdersByPatient(patient);
		
		Assert.assertTrue(CollectionUtils.isEmpty(encounters));
		Assert.assertTrue(CollectionUtils.isEmpty(observations));
		Assert.assertTrue(CollectionUtils.isEmpty(orders));
	}