	@Test
	public void isCollectionField_shouldReturnTrueIfGivenFieldIsCollectionAndItsElementTypeIsGivenParameterized()
	{
		Reflect reflect = new Reflect(OpenmrsObject.class);
		List<Field> allFields = Reflect.getAllFields(OpenmrsObjectImp.class);
		
		Assert.assertEquals("subClassField", allFields.get(1).getName());
		Assert.assertTrue(reflect.isCollectionField(allFields.get(1)));
	}