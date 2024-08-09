    @Test
    public void getNewest() {
        WordSequence ws = asWordSequence(dictionary, "one", "two", "three");
        assertThat(asWordSequence(dictionary, "two", "three"),
                equalTo(ws.getNewest()));
        assertThat(ws.getNewest().getOldest(), equalTo(ws.getOldest()
                .getNewest()));
    }