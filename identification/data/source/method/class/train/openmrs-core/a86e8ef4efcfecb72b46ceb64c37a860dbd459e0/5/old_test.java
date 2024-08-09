	@Test
	public void handle_shouldVoidTheOrdersEncountersAndObservationsAssociatedWithThePatient() {
		Patient patient = Context.getPatientService().getPatient(7);
		Assert.assertFalse(patient.getVoided());
		
		List<Encounter> encounters = Context.getEncounterService().getEncountersByPatient(patient);
		List<Obs> observations = Context.getObsService().getObservationsByPerson(patient);
		List<Order> orders = Context.getOrderService().getAllOrdersByPatient(patient);
		
		//we should have some unvoided encounters, obs and orders for the test to be concrete
		assertTrue(CollectionUtils.isNotEmpty(encounters));
		assertTrue(CollectionUtils.isNotEmpty(observations));
		assertTrue(CollectionUtils.isNotEmpty(orders));
		
		//check that fields to be set by the handler are initially null 
		for (Encounter encounter : encounters) {
			assertNull(encounter.getDateVoided());
			assertNull(encounter.getVoidedBy());
			assertNull(encounter.getVoidReason());
		}
		for (Obs obs : observations) {
			assertNull(obs.getDateVoided());
			assertNull(obs.getVoidedBy());
			assertNull(obs.getVoidReason());
		}
		for (Order order : orders) {
			assertNull(order.getDateVoided());
			assertNull(order.getVoidedBy());
			assertNull(order.getVoidReason());
		}
		
		new PatientDataVoidHandler().handle(patient, new User(1), new Date(), "voidReason");
		
		//all encounters void related fields should have been set
		for (Encounter encounter : encounters) {
			assertTrue(encounter.getVoided());
			assertNotNull(encounter.getDateVoided());
			assertNotNull(encounter.getVoidedBy());
			assertNotNull(encounter.getVoidReason());
		}
		//all obs void related fields should have been set
		for (Obs obs : observations) {
			assertTrue(obs.getVoided());
			assertNotNull(obs.getDateVoided());
			assertNotNull(obs.getVoidedBy());
			assertNotNull(obs.getVoidReason());
		}
		//all order void related fields should have been set
		for (Order order : orders) {
			assertTrue(order.getVoided());
			assertNotNull(order.getDateVoided());
			assertNotNull(order.getVoidedBy());
			assertNotNull(order.getVoidReason());
		}
		
		//refresh the lists and check that all encounters, obs and orders were voided
		encounters = Context.getEncounterService().getEncountersByPatient(patient);
		observations = Context.getObsService().getObservationsByPerson(patient);
		
		assertTrue(CollectionUtils.isEmpty(encounters));
		assertTrue(CollectionUtils.isEmpty(observations));
	}