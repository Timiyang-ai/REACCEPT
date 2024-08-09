@Test
public void shouldReturnEmptyStringIfValueIsNull() {
    PersonEditor editor = new PersonEditor();
    editor.setValue(null);
    
    assertThat(editor.getAsText(), is(nullValue()));
}