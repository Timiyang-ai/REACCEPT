@Test
    public void testEquals() {
        final String text = "equals";
        MatcherAssert.assertThat(
            "Envelope value does not match its represented String value",
            new TextEnvelopeDummy(text),
            new IsEqual<>(text)
        );
    }