    @Test
    public void testHSLToColor() {
        for (TestEntry entry : sEntryList) {
            verifyHSLToColor(entry.hsl, entry.rgb);
        }
    }