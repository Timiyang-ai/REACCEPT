	@Test
	public void isSuperClass_shouldReturnFalseIfGivenSubClassIsNotAccessibleFromGivenParameterizedClass() {
		Reflect reflect = new Reflect(OpenmrsObject.class);
		
		Assert.assertFalse(reflect.isSuperClass(new NormalClass()));
	}