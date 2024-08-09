    @Test
    public void hasColoredText() {
        assertThat(textFlow, TextFlowMatchers.hasColoredText("foobar <RED>quux</RED>"));
    }