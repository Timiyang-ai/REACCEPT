@Test
	public void validate_shouldFailIfAnyMemberIsInvalid() throws Exception {
		User provider = new User();
		provider.setUsername("test");
		Patient patient = new Patient(6);
		
		OrderGroup group = new OrderGroup(null, patient);
		group.setCreator(provider);
		group.setDateCreated(new Date());
		Order order = new Order();
		// we don't setting order's concept so it should be detected
		group.addOrder(order);
		
		Errors errors = new BindException(group, "group");
		getValidator().validate(group, errors);
		
		Assert.assertTrue(errors.hasErrors());
		Assert.assertFalse(errors.hasFieldErrors("creator"));
		Assert.assertFalse(errors.hasFieldErrors("patient"));
		Assert.assertFalse(errors.hasFieldErrors("dateCreated"));
	}