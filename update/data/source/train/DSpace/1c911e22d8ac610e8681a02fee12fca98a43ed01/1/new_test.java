@Test
    public void testGetSize()
    {
        long size = 238413;  // yuck, hardcoded!
        assertThat("testGetSize 0", bs.getSizeBytes(), equalTo(size));
    }