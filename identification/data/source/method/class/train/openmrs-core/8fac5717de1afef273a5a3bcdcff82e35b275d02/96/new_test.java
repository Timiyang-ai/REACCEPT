	@Test
	public void getFormField_shouldIgnoreFormFieldsPassedToIgnoreFormFields() {
		
		executeDataSet(INITIAL_FIELDS_XML);
		executeDataSet("org/openmrs/api/include/FormServiceTest-formFields.xml");
		
		FormField ff = Context.getFormService().getFormField(new Form(1), new Concept(1), null, false);
		assertNotNull(ff); // sanity check
		
		// test that the first formfield is ignored when a second fetch
		// is done on the same form and same concept
		List<FormField> ignoreFormFields = new ArrayList<>();
		ignoreFormFields.add(ff);
		FormField ff2 = Context.getFormService().getFormField(new Form(1), new Concept(1), ignoreFormFields, false);
		assertNotNull(ff2);
		assertNotSame(ff, ff2);
		
	}