@Override
	@Test
	public void shouldReturnEmptyStringIfValueIsNull() {
		
		assertThat(editor.getAsText(), is(nullValue()));
	}