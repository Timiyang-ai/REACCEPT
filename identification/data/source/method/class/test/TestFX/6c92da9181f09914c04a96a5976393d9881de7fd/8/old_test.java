    @Test
    public void hasExactlyColoredText_fails() {
        assertThatThrownBy(() -> assertThat(exactTextFlow,
                TextFlowMatchers.hasExactlyColoredText("<LIMEGREEN>exact</LIMEGREEN>")))
                .isExactlyInstanceOf(AssertionError.class)
                .hasMessage("\nExpected: TextFlow has exactly colored text \"<LIMEGREEN>exact</LIMEGREEN>\"\n     " +
                        "but: was impossible to exactly match TextFlow containing " +
                        "colored text: \"exact\" which has color: \"#33cd32\".\n" +
                        "This is not a named color. The closest named color is: \"LIMEGREEN\".\n" +
                        "See: https://docs.oracle.com/javase/9/docs/api/javafx/scene/doc-files/cssref.html#typecolor");
    }