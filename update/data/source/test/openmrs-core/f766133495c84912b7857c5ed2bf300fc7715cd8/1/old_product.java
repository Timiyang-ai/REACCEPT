@Test
	public void setAsText_shouldSetUsingUuid() {
		DrugEditor drugEditor = new DrugEditor();
		drugEditor.setAsText("3cfcf118-931c-46f7-8ff6-7b876f0d4202");
		Assert.assertNotNull(drugEditor.getValue());
	}