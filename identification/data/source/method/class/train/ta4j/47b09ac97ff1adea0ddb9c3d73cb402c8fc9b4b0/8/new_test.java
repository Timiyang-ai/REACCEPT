    @Test
    public void getCurrentTrade() {
        assertTrue(emptyRecord.getCurrentTrade().isNew());
        assertTrue(openedRecord.getCurrentTrade().isOpened());
        assertTrue(closedRecord.getCurrentTrade().isNew());
    }