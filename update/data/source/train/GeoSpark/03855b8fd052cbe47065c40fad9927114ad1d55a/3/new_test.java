@Test
    public void testGetSampleNumbers() throws Exception {
        assertEquals(10, RDDSampleUtils.getSampleNumbers(2, 10));
        assertEquals(100, RDDSampleUtils.getSampleNumbers(2, 100));
        assertEquals(10, RDDSampleUtils.getSampleNumbers(5, 1000));
        assertEquals(100, RDDSampleUtils.getSampleNumbers(5, 10000));
        assertEquals(100, RDDSampleUtils.getSampleNumbers(5, 10001));
        assertEquals(1000, RDDSampleUtils.getSampleNumbers(5, 100011));
        assertEquals(1000, RDDSampleUtils.getSampleNumbers(6, 100011));
    }