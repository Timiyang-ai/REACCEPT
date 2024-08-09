    @Test
    public void checkReferenceTest() throws Exception {
        when(config.getAlpha()).thenReturn(0.001d);
        Map<Hash, Integer> map = new HashMap<Hash, Integer>(){{put(REFERENCE, 0);}};
        tipSelector.checkReference(REFERENCE, map, null);
        //test passes if no exceptions are thrown
    }