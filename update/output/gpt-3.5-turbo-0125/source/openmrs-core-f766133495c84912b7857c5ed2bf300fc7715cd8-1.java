@Override
@Test
public void shouldReturnEmptyStringIfValueIsNull() {
    LocationTagEditor editor = new LocationTagEditor();
    
    editor.setValue(null);
    
    assertThat(editor.getAsText(), is(nullValue()));
}