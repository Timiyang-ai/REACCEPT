	@Test
	public void saveFormField_shouldPropagateSaveToTheFieldPropertyOnTheGivenFormField() {
		// create a new Field
		Field field = new Field();
		field.setName("This is a new field");
		field.setDescription("It should be saved along with the formField");
		
		// put that field on a new FormField.
		FormField formField = new FormField();
		formField.setField(field);
		formField.setForm(new Form(1));
		
		// save the FormField
		Context.getFormService().saveFormField(formField);
		
		// the uuid should be set by this method so that the field can be saved successfully
		Assert.assertNotNull(field.getUuid());
	}