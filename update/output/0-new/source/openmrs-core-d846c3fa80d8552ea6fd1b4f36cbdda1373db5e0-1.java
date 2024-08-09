@Test
	@Verifies(value = "should unvoid the orders and encounters associated with the patient", method = "handle(Patient,User,Date,String)")
	public void handle_shouldUnvoidTheOrdersAndEncountersAssociatedWithThePatient() throws Exception {
		Patient patient = Context.getPatientService().getPatient(7);
		patient = Context.getPatientService().voidPatient(patient, "Void Reason");
		Assert.assertTrue(patient.isVoided());
		
		EncounterService es = Context.getEncounterService();
		List<Encounter> encounters = es.getEncounters(patient, null, null, null, null, null, null, true);
		Assert.assertTrue(CollectionUtils.isNotEmpty(encounters));
		//all encounters void related fields should be null
		for (Encounter encounter : encounters) {
			Assert.assertTrue(encounter.isVoided());
			Assert.assertNotNull(encounter.getDateVoided());
			Assert.assertNotNull(encounter.getVoidedBy());
			Assert.assertNotNull(encounter.getVoidReason());
		}
		
		OrderService os = Context.getOrderService();
		List<Patient> patients = new ArrayList<Patient>();
		patients.add(patient);
		List<Order> orders = os.getOrders(null, patients, null, null, null);
		Assert.assertTrue(CollectionUtils.isNotEmpty(orders));
		//all order void related fields should be null
		for (Order order : orders) {
			Assert.assertTrue(order.isVoided());
			Assert.assertNotNull(order.getDateVoided());
			Assert.assertNotNull(order.getVoidedBy());
			Assert.assertNotNull(order.getVoidReason());
		}
		
		User user = Context.getUserService().getUser(1);
		new PatientDataUnvoidHandler().handle(patient, user, patient.getDateVoided(), null);
		
		//check that the voided related fields were set null 
		for (Encounter encounter : encounters) {
			Assert.assertFalse(encounter.isVoided());
			Assert.assertNull(encounter.getDateVoided());
			Assert.assertNull(encounter.getVoidedBy());
			Assert.assertNull(encounter.getVoidReason());
		}
		for (Order order : orders) {
			Assert.assertFalse(order.isVoided());
			Assert.assertNull(order.getDateVoided());
			Assert.assertNull(order.getVoidedBy());
			Assert.assertNull(order.getVoidReason());
		}
	}