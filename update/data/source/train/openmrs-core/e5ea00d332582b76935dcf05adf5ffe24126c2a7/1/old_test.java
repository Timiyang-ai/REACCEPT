@Test
	public void setAsText_shouldSetUsingUuid() {
		LocationTagEditor editor = new LocationTagEditor();
		editor.setAsText("001e503a-47ed-11df-bc8b-001e378eb67e");
		Assert.assertNotNull(editor.getValue());
	}