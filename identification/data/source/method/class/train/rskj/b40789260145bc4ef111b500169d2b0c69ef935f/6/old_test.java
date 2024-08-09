    @Test
    public void recordEvent() {
        PeerScoring scoring = new PeerScoring();

        scoring.recordEvent(EventType.INVALID_BLOCK);

        Assert.assertEquals(1, scoring.getEventCounter(EventType.INVALID_BLOCK));
        Assert.assertEquals(0, scoring.getEventCounter(EventType.INVALID_TRANSACTION));
        Assert.assertEquals(1, scoring.getTotalEventCounter());
    }