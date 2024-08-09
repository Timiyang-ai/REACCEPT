@Test
public void shouldReturnEmptyStringIfValueIsNull() {
    DrugEditor editor = new DrugEditor();
    assertThat(editor.getAsText(), is(nullValue()));
}