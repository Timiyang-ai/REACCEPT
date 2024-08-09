@Test
    public void validate_shouldFailIfGroupDoesntHaveAnyMembers() throws Exception {
    	User provider = Context.getUserService().getUser(501);
		Patient patient = Context.getPatientService().getPatient(6);
		
		OrderGroup group = new OrderGroup(null, patient);
		group.setCreator(provider);
		group.setDateCreated(new Date());
		
		Errors errors = new BindException(group, "group");
		validator.validate(group, errors);
		
		Assert.assertTrue(errors.hasErrors());
		Assert.assertFalse(errors.hasFieldErrors("creator"));
		Assert.assertFalse(errors.hasFieldErrors("patient"));
		Assert.assertFalse(errors.hasFieldErrors("dateCreated"));
    }