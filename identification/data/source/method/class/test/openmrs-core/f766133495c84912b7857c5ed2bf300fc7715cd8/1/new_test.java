@Override
	@Ignore("to investigate, this behavior deviates from most openmrs propertyeditors")
	@Test(expected = IllegalArgumentException.class)
	public void shouldFailToSetTheEditorValueIfGivenUuidDoesNotExist() {
		
		editor.setAsText(getNonExistingObjectUuid());
	}