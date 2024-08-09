	@Test
	public void mergeDuplicateFields_shouldMergeDuplicateFieldsInFormFieldsAndThenPurgeTheDuplicateFields() {
		
		executeDataSet(INITIAL_FIELDS_XML);
		executeDataSet(FORM_FIELDS_XML);
		
		Context.getFormService().mergeDuplicateFields();
		
		// duplicateField should no longer be referenced
		Assert.assertNull(Context.getFormService().getFieldByUuid("b1843148-da2f-4349-c9c7-1164b98d91dd"));
		
		// duplicateField should be purged
		assertEquals(2, Context.getFormService().getAllFields().size());
	}