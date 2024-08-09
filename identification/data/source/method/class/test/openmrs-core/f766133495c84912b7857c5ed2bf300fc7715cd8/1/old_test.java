@Test
	public void setAsText_shouldSetUsingUuid() {
		PersonEditor editor = new PersonEditor();
		editor.setAsText("da7f524f-27ce-4bb2-86d6-6d1d05312bd5");
		Assert.assertNotNull(editor.getValue());
	}