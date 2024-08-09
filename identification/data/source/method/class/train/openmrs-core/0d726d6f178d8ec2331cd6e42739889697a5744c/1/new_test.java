	@Test
	public void setAsText_shouldSetUsingConceptId() {
		ProgramEditor editor = new ProgramEditor();
		editor.setAsText("concept.9");
		Assert.assertNotNull(editor.getValue());
	}