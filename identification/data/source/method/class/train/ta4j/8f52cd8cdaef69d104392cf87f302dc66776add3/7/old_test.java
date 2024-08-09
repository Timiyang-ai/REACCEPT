    @Test
    public void operate() {
        TradingRecord record = new BaseTradingRecord();

        record.operate(1);
        assertTrue(record.getCurrentTrade().isOpened());
        assertEquals(0, record.getTradeCount());
        assertNull(record.getLastTrade());
        assertEquals(Order.buyAt(1, NaN, NaN), record.getLastOrder());
        assertEquals(Order.buyAt(1, NaN, NaN), record.getLastOrder(Order.OrderType.BUY));
        assertNull(record.getLastOrder(Order.OrderType.SELL));
        assertEquals(Order.buyAt(1, NaN, NaN), record.getLastEntry());
        assertNull(record.getLastExit());

        record.operate(3);
        assertTrue(record.getCurrentTrade().isNew());
        assertEquals(1, record.getTradeCount());
        assertEquals(new Trade(Order.buyAt(1, NaN, NaN), Order.sellAt(3, NaN, NaN)), record.getLastTrade());
        assertEquals(Order.sellAt(3, NaN, NaN), record.getLastOrder());
        assertEquals(Order.buyAt(1, NaN, NaN), record.getLastOrder(Order.OrderType.BUY));
        assertEquals(Order.sellAt(3, NaN, NaN), record.getLastOrder(Order.OrderType.SELL));
        assertEquals(Order.buyAt(1, NaN, NaN), record.getLastEntry());
        assertEquals(Order.sellAt(3, NaN, NaN), record.getLastExit());

        record.operate(5);
        assertTrue(record.getCurrentTrade().isOpened());
        assertEquals(1, record.getTradeCount());
        assertEquals(new Trade(Order.buyAt(1, NaN, NaN), Order.sellAt(3, NaN, NaN)), record.getLastTrade());
        assertEquals(Order.buyAt(5, NaN, NaN), record.getLastOrder());
        assertEquals(Order.buyAt(5, NaN, NaN), record.getLastOrder(Order.OrderType.BUY));
        assertEquals(Order.sellAt(3, NaN, NaN), record.getLastOrder(Order.OrderType.SELL));
        assertEquals(Order.buyAt(5, NaN, NaN), record.getLastEntry());
        assertEquals(Order.sellAt(3, NaN, NaN), record.getLastExit());
    }