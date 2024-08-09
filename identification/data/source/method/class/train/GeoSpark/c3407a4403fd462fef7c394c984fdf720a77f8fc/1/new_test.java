@Test
    public void testGetSampleNumbers() {
        assertEquals(10, RDDSampleUtils.getSampleNumbers(2, 10,-1));
        assertEquals(100, RDDSampleUtils.getSampleNumbers(2, 100,-1));
        assertEquals(10, RDDSampleUtils.getSampleNumbers(5, 1000,-1));
        assertEquals(100, RDDSampleUtils.getSampleNumbers(5, 10000,-1));
        assertEquals(100, RDDSampleUtils.getSampleNumbers(5, 10001,-1));
        assertEquals(1000, RDDSampleUtils.getSampleNumbers(5, 100011,-1));
        assertEquals(99, RDDSampleUtils.getSampleNumbers(6, 100011,99));
        assertEquals(999, RDDSampleUtils.getSampleNumbers(20, 999,-1));
        assertEquals(40, RDDSampleUtils.getSampleNumbers(20, 1000,-1));
    }