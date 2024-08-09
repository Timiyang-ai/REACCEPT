@Test
	public void validate_shouldFailValidationIfCauseOfDeathIsBlankWhenPatientIsDead() {
		Patient pa = new Patient(1);
		pa.setDead(true);
		
		Errors errors = new BindException(pa, "patient");
		validator.validate(pa, errors);
		
		Assert.assertTrue(errors.hasFieldErrors("causeOfDeath"));
		Assert.assertEquals("Person.dead.causeOfDeathAndCauseOfDeathNonCodedNull", errors.getFieldError("causeOfDeath").getCode());
		
	}