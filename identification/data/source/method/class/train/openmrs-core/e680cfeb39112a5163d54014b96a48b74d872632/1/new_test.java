	@Test
	public void getInheritedFields_shouldReturnOnlyTheSubClassFieldsOfGivenParameterizedClass() {
		Reflect reflect = new Reflect(OpenmrsObject.class);
		List<Field> fields = reflect.getInheritedFields(OpenmrsObjectImp.class);
		
		List<Field> allFields = Reflect.getAllFields(OpenmrsObjectImp.class);
		
		findFieldByName(fields, "subClassField");
		findFieldByName(fields, "nonCollectionField");
		findFieldByName(fields, "genericCollectionField");
		
		Field normalClassField = findFieldByName(allFields, "normalClassField");
		Assert.assertFalse(fields.contains(normalClassField));
	}