    @Test
    public void hasText() {
        assertThat(textFlow, TextFlowMatchers.hasText("foobar quux"));
    }