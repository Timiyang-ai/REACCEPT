	@Test
	public void getFieldTypeByUuid_shouldFindObjectGivenValidUuid() {
		String uuid = "e7016b7d-39a5-4911-89da-0eefbfef7cb5";
		FieldType fieldType = Context.getFormService().getFieldTypeByUuid(uuid);
		assertEquals(2, (int) fieldType.getFieldTypeId());
	}