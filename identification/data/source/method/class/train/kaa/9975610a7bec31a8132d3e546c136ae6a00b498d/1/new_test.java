@Test
    public void testAddOpsServerLoad() {
        OperationsServerLoadHistory hist = new OperationsServerLoadHistory(MAX_HISTORY_TIME_LIVE);
        hist.addOpsServerLoad(new LoadInfo(2));
        fillOutHistory(hist,1000,5);
        assertNotNull(hist.getHistory());
        if (hist.getHistory().size() >= 5) {
            fail("testAddOpsServerLoad, removeOld history failed, size should be no more than 4, but "+hist.getHistory().size());
        }
    }