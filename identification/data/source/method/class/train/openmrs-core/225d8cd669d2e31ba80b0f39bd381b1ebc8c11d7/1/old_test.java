	@Test
	public void validate_shouldFailValidationIfPersonMergeLogDataIsNull() {
		PersonMergeLog personMergeLog = new PersonMergeLog();
		personMergeLog.setWinner(new Person());
		personMergeLog.setLoser(new Person());
		PersonMergeLogValidator validator = new PersonMergeLogValidator();
		Errors errors = new BindException(personMergeLog, "personMergeLog");
		validator.validate(personMergeLog, errors);
		Assert.assertTrue(errors.hasFieldErrors("personMergeLogData"));
	}