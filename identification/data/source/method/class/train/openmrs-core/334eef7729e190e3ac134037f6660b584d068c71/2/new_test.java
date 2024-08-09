@Test
	public void saveOrderGroup_shouldSaveNewOrderGroup() throws Exception {
		User provider = Context.getUserService().getUser(501);
		Patient patient = Context.getPatientService().getPatient(6);
		
		OrderGroup group = new OrderGroup(null, patient);
		group.setCreator(provider);
		Order order = new Order();
		order.setConcept(Context.getConceptService().getConcept(23));
		group.addOrder(order);
		
		group = Context.getOrderService().saveOrderGroup(group);
		
		Assert.assertNotNull(group);
		
	}