	@Test
	public void setAsText_shouldSetUsingName() {
		PrivilegeEditor editor = new PrivilegeEditor();
		editor.setAsText("Some Privilege");
		Assert.assertNotNull(editor.getValue());
	}