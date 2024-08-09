	@Test(expected = APIException.class)
	public void validate_shouldThrowThrowAPIExceptionIfTheInputIsNull() {
		BindException errors = new BindException(new Object(), "");
		adminService.validate(null, errors);
	}