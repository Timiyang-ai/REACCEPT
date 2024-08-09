@Test
	public void validate_shouldFailIfAnyRequiredFieldIsNull() throws Exception {
		
		// we are setting all required order to null field to be verified 
		OrderGroup group = new OrderGroup(null, null);
		group.setCreator(null);
		group.setDateCreated(null);
		Order order = new Order();
		order.setConcept(new Concept(23));
		group.addOrder(order);
		
		Errors errors = new BindException(group, "group");
		getValidator().validate(group, errors);
		
		Assert.assertTrue(errors.hasFieldErrors("creator"));
		Assert.assertTrue(errors.hasFieldErrors("patient"));
		Assert.assertTrue(errors.hasFieldErrors("dateCreated"));
	}