@Test
	public void handle_shouldUnvoidTheOrdersAndEncountersAssociatedWithThePatient() {
		Patient patient = Context.getPatientService().getPatient(7);
		patient = Context.getPatientService().voidPatient(patient, "Void Reason");
		assertTrue(patient.getVoided());
		
		EncounterService es = Context.getEncounterService();
		EncounterSearchCriteria encounterSearchCriteria = new EncounterSearchCriteriaBuilder()
			.setPatient(patient)
			.setIncludeVoided(true)
			.createEncounterSearchCriteria();
		List<Encounter> encounters = es.getEncounters(encounterSearchCriteria);
		assertTrue(CollectionUtils.isNotEmpty(encounters));
		//all encounters void related fields should be null
		for (Encounter encounter : encounters) {
			assertTrue(encounter.getVoided());
			assertNotNull(encounter.getDateVoided());
			assertNotNull(encounter.getVoidedBy());
			assertNotNull(encounter.getVoidReason());
		}
		
		OrderService os = Context.getOrderService();
		List<Order> orders = os.getAllOrdersByPatient(patient);
		assertFalse(orders.isEmpty());
		//all order void related fields should be null
		for (Order order : orders) {
			assertTrue(order.getVoided());
			assertNotNull(order.getDateVoided());
			assertNotNull(order.getVoidedBy());
			assertNotNull(order.getVoidReason());
		}
		
		User user = Context.getUserService().getUser(1);
		new PatientDataUnvoidHandler().handle(patient, user, patient.getDateVoided(), null);
		
		//check that the voided related fields were set null 
		for (Encounter encounter : encounters) {
			assertFalse(encounter.getVoided());
			assertNull(encounter.getDateVoided());
			assertNull(encounter.getVoidedBy());
			assertNull(encounter.getVoidReason());
		}
		for (Order order : orders) {
			assertFalse(order.getVoided());
			assertNull(order.getDateVoided());
			assertNull(order.getVoidedBy());
			assertNull(order.getVoidReason());
		}
	}