	@Test
	public void saveForm_shouldSaveGivenFormSuccessfully() {
		FormService fs = Context.getFormService();
		createFormsLockedGPAndSetValue("false");
		
		Form form = new Form();
		form.setName("new form");
		form.setVersion("1.0");
		form.setDescription("testing TRUNK-4030");
		
		Form formSave = fs.saveForm(form);
		
		assertTrue(form.equals(formSave));
	}