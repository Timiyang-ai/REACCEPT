@Test
    public void testEquals() {
        final String text = "equals";
        MatcherAssert.assertThat(
            "Envelope does not match text representing the same value",
            new TextEnvelopeDummy(text),
            new IsEqual<>(new TextOf(text))
        );
    }