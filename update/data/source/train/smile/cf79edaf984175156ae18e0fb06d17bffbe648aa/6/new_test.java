@Test
    public void testGetTermFrequency() {
        System.out.println("getTermFrequency");
        assertEquals(27, corpus.getTermFrequency("romantic"));
    }