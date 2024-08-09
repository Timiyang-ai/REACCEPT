    @Test
    public void getOldest() {
        WordSequence ws = asWordSequence(dictionary, "zero", "six", "one");
        assertThat(asWordSequence(dictionary, "zero", "six"),
                equalTo(ws.getOldest()));
        assertThat(ws.getOldest().getOldest(),
                equalTo(new WordSequence(ws.getWord(0))));
    }