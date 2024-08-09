	@Test
	public void getForms_shouldReturnDuplicateFormWhenGivenFieldsIncludedInFormMultipleTimes() {
		executeDataSet(INITIAL_FIELDS_XML);
		executeDataSet("org/openmrs/api/include/FormServiceTest-formFields.xml");
		
		FormService formService = Context.getFormService();
		
		List<Field> fields = new ArrayList<>();
		fields.add(new Field(1));
		
		List<Form> forms = formService.getForms(null, null, null, null, null, null, fields);
		
		assertEquals(3, forms.size());
	}