	@Test
	public void setAsText_shouldSetUsingName() {
		RoleEditor editor = new RoleEditor();
		editor.setAsText("Provider");
		Assert.assertNotNull(editor.getValue());
	}