	@Test
	public void validate_shouldFailValidationIfNameIsNull() {
		Field ff = new Field();
		ff.setName(null);
		FieldType ft = new FieldType();
		Boolean retired = Boolean.FALSE;
		ft.setId(0xdeadcafe);
		ff.setFieldType(ft);
		ff.setRetired(retired);
		Boolean multiple = Boolean.FALSE;
		ff.setSelectMultiple(multiple);
		
		Errors errors = new BindException(ff, "name");
		new FieldValidator().validate(ff, errors);
		
		Assert.assertTrue(errors.hasFieldErrors("name"));
	}