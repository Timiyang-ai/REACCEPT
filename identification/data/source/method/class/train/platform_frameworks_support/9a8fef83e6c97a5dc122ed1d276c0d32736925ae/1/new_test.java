    @Test
    public void testLABToColor() {
        for (TestEntry entry : sEntryList) {
            verifyLABToColor(entry.lab, entry.rgb);
        }
    }