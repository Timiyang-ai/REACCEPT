	@Test
	public void getFormByUuid_shouldFindObjectGivenValidUuid() {
		String uuid = "d9218f76-6c39-45f4-8efa-4c5c6c199f50";
		Form form = Context.getFormService().getFormByUuid(uuid);
		assertEquals(1, (int) form.getFormId());
	}