	@Test
	public void getAllFields_shouldReturnAllFieldsIncludePrivateAndSuperClasses() {
		List<Field> allFields = Reflect.getAllFields(OpenmrsObjectImp.class);
		
		findFieldByName(allFields, "subClassField");
		findFieldByName(allFields, "normalClassField");
		findFieldByName(allFields, "nonCollectionField");
		findFieldByName(allFields, "genericCollectionField");
	}