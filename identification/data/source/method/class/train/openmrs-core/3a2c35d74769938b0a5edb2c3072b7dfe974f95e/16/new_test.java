	@Test
	public void duplicateForm_shouldClearChangedDetailsAndUpdateCreationDetails() {
		Date startOfTest = DateUtil.truncateToSeconds(new Date());
		FormService formService = Context.getFormService();
		Form form = formService.getForm(1);
		
		Form dupForm = formService.duplicateForm(form);
		
		Assert.assertNull(dupForm.getChangedBy());
		Assert.assertNull(dupForm.getDateChanged());
		assertEquals(Context.getAuthenticatedUser(), dupForm.getCreator());
		assertFalse(dupForm.getDateCreated().before(startOfTest));
	}