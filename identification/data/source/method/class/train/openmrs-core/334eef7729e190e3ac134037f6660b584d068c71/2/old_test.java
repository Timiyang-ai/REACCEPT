@Test
	public void saveOrderGroup_shouldSaveNewOrderGroup() throws Exception {
		User provider = Context.getUserService().getUser(501);
		Patient patient = Context.getPatientService().getPatient(6);
		
		OrderGroup group = new OrderGroup(null, patient);
		group.setCreator(provider);
		group.setDateCreated(new Date());
		Order order = new Order();
		order.setOrderNumber("1");
		order.setDateCreated(new Date());
		order.setConcept(Context.getConceptService().getConcept(23));
		order.setPatient(patient);
		group.addOrder(order);
		
		group = Context.getOrderService().saveOrderGroup(group);
		
		Assert.assertNotNull(group);
		
	}