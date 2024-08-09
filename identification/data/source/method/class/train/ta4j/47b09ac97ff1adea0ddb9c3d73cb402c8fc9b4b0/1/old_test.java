    @Test
    public void getLastTrade() {
        assertNull(emptyRecord.getLastTrade());
        assertEquals(new Trade(Order.buyAt(0, NaN, NaN), Order.sellAt(3, NaN, NaN)), openedRecord.getLastTrade());
        assertEquals(new Trade(Order.buyAt(7, NaN, NaN), Order.sellAt(8, NaN, NaN)), closedRecord.getLastTrade());
    }