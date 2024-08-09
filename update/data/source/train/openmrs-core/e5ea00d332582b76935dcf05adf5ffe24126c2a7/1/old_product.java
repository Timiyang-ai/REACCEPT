@Test
	public void setAsText_shouldSetUsingUuid() {
		CohortEditor editor = new CohortEditor();
		editor.setAsText("h9a9m0i6-15e6-467c-9d4b-mbi7teu9lf0f");
		Assert.assertNotNull(editor.getValue());
	}