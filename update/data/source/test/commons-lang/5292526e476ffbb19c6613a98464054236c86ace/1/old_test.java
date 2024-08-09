@Test
    public void testResolveVariable() {
        final StrBuilder builder = new StrBuilder("Hi ${name}!");
        Map<String, String> map = new HashMap<String, String>();
        map.put("name", "commons");
        StrSubstitutor sub = new StrSubstitutor(map) {
            @Override
            protected String resolveVariable(final String variableName, final StrBuilder buf, final int startPos, final int endPos) {
                assertEquals("name", variableName);
                assertSame(builder, buf);
                assertEquals(3, startPos);
                assertEquals(10, endPos);
                return "jakarta";
            }
        };
        sub.replaceIn(builder);
        assertEquals("Hi jakarta!", builder.toString());
    }