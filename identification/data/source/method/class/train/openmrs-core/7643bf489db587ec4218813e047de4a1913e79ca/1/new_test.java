@Test
	@Verifies(value = "should fail validation if unlocalized name is null or empty or whitespace", method = "validate(Object,Errors)")
	public void validate_shouldFailValidationIfUnlocalizedNameIsNullOrEmptyOrWhitespace() throws Exception {
		TaskDefinition def = new TaskDefinition();
		def.setName(null);
		def.setRepeatInterval(3600000L);
		def.setTaskClass("org.openmrs.scheduler.tasks.HelloWorldTask");
		
		Errors errors = new BindException(def, "def");
		new SchedulerFormValidator().validate(def, errors);
		Assert.assertTrue(errors.hasFieldErrors("localizedName.unlocalizedValue"));
		
		def.setName("");
		errors = new BindException(def, "def");
		new SchedulerFormValidator().validate(def, errors);
		Assert.assertTrue(errors.hasFieldErrors("localizedName.unlocalizedValue"));
		
		def.setName(" ");
		errors = new BindException(def, "def");
		new SchedulerFormValidator().validate(def, errors);
		Assert.assertTrue(errors.hasFieldErrors("localizedName.unlocalizedValue"));
	}