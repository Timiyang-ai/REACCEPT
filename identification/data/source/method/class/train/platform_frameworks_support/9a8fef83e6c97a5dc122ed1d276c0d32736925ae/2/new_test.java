    @Test
    public void testLABToXYZ() {
        for (TestEntry entry : sEntryList) {
            verifyLABToXYZ(entry.lab, entry.xyz);
        }
    }