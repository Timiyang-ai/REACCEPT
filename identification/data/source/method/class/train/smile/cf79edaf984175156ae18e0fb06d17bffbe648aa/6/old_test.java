@Test
    public void testGetTermFrequency() {
        System.out.println("getTermFrequency");
        assertEquals(50, corpus.getTermFrequency("romantic"));
    }