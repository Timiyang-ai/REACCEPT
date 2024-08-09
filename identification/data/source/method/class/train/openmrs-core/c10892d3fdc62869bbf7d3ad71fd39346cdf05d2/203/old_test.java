	@Test
	public void getAllForms_shouldReturnAllForms() {
		List<Form> forms = Context.getFormService().getAllForms();
		int currentFormsSize = forms.size();
		assertEquals(1, currentFormsSize);
		
		Context.getFormService().saveForm(createMockForm(false));
		
		forms = Context.getFormService().getAllForms();
		assertEquals(currentFormsSize + 1, forms.size());
	}