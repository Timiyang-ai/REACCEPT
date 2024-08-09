    @Test
    public void testString() {

        for (String s : new String[] { "", "foobar", "\uABCD", "驯鹿" }) {
            assertEquals(s, formatter.format(s));
            assertEquals(s, formatter.parse(s, String.class));
        }
    }