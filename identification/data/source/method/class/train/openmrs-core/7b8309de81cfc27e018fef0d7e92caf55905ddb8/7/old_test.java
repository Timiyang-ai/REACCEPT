@Test
	@Verifies(value = "should unvoid the orders and encounters associated with the patient", method = "handle(Patient,User,Date,String)")
	public void handle_shouldUnvoidTheOrdersAndEncountersAssociatedWithThePatient() throws Exception {
		Patient patient = Context.getPatientService().getPatient(7);
		OrderService os = Context.getOrderService();
		List<Patient> patients = new ArrayList<Patient>();
		patients.add(patient);
		//should have some un voided orders
		Assert.assertTrue(CollectionUtils
		        .isNotEmpty(os.getOrders(Order.class, patients, null, null, null, null, null, null)));
		
		patient = Context.getPatientService().voidPatient(patient, "Void Reason");
		Assert.assertTrue(patient.isVoided());
		//all orders should have been voided
		Assert.assertTrue(CollectionUtils.isEmpty(os.getOrders(Order.class, patients, null, null, null, null, null, null)));
		
		EncounterService es = Context.getEncounterService();
		List<Encounter> encounters = es.getEncounters(patient, null, null, null, null, null, null, null, null, true);
		Assert.assertTrue(CollectionUtils.isNotEmpty(encounters));
		//all encounters void related fields should be null
		for (Encounter encounter : encounters) {
			Assert.assertTrue(encounter.isVoided());
			Assert.assertNotNull(encounter.getDateVoided());
			Assert.assertNotNull(encounter.getVoidedBy());
			Assert.assertNotNull(encounter.getVoidReason());
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
		
		List<Order> orders = os.getOrders(Order.class, patients, null, null, null, null, null, null);
		for (Order order : orders) {
			Assert.assertFalse(order.isVoided());
			Assert.assertNull(order.getDateVoided());
			Assert.assertNull(order.getVoidedBy());
			Assert.assertNull(order.getVoidReason());
		}
	}