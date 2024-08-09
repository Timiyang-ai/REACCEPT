	@Test
	public void saveFieldType_shouldCreateNewFieldType() {
		FieldType fieldType = new FieldType();
		
		fieldType.setName("testing");
		fieldType.setDescription("desc");
		fieldType.setIsSet(true);
		
		FormService formService = Context.getFormService();
		
		formService.saveFieldType(fieldType);
		
		Assert.assertNotNull(formService.getFieldType(fieldType.getFieldTypeId()));
	}