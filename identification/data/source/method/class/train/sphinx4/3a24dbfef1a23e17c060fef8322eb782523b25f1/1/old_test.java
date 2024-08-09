    @Test
    public void equals() {
        WordSequence ws = asWordSequence(dictionary, "one", "two", "three");
        assertThat(ws.size(), is(3));
        assertThat(asWordSequence(dictionary, "one", "two", "three"),
                equalTo(ws));
    }