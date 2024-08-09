	@Test
	public void validate_shouldFailValidationIfNameIsNullOrEmptyOrWhitespace() {
		TaskDefinition def = new TaskDefinition();
		def.setName(null);
		def.setRepeatInterval(3600000L);
		def.setTaskClass("org.openmrs.scheduler.tasks.HelloWorldTask");
		
		Errors errors = new BindException(def, "def");
		new SchedulerFormValidator().validate(def, errors);
		Assert.assertTrue(errors.hasFieldErrors("name"));
		
		def.setName("");
		errors = new BindException(def, "def");
		new SchedulerFormValidator().validate(def, errors);
		Assert.assertTrue(errors.hasFieldErrors("name"));
		
		def.setName(" ");
		errors = new BindException(def, "def");
		new SchedulerFormValidator().validate(def, errors);
		Assert.assertTrue(errors.hasFieldErrors("name"));
	}