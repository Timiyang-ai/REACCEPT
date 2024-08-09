@Test
    public void testGetRapidDefaultReadNameRegexSplit() {
        final OpticalDuplicateFinder opticalDuplicateFinder = new OpticalDuplicateFinder();
        for (int i = 1; i < 10; i++) {
            doTestGetRapidDefaultReadNameRegexSplit((i <= 5) ? i : 5, opticalDuplicateFinder);
        }
    }