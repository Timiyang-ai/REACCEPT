	@Test
	public void getForm_shouldReturnNullIfFormNotFound() {
		List<Form> forms = Context.getFormService().getAllForms();
		boolean formNameFound = false;
		final String formName = "Sample_Form_Not_In_List";
		for (Form node:forms) {
			if (node.getName().equals(formName)) {
				formNameFound = true;
			}
		}
		assertFalse(formNameFound);
		
		Form form = Context.getFormService().getForm(formName);
		assertNull(form);
	}