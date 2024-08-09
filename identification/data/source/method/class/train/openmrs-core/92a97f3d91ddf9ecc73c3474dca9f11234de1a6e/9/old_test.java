	@Test
	public void validate_shouldFailValidationIfRetiredAndRetireReasonIsNull() {
		String retireReason = null;
		invokeValidateAndAssertHasErrorRetireReason(retireReason);
	}