@Test
    public void testPoll() throws Exception {
        Collection<AbstractTransaction> added = this.loadQueue(NUM_TXNS);
        assertEquals(added.size(), this.queue.size());
        
        Iterator<AbstractTransaction> it = added.iterator();
        for (int i = 0; i < NUM_TXNS; i++) {
            ThreadUtil.sleep(TXN_DELAY);
            EstTimeUpdater.update(System.currentTimeMillis());
            assertEquals(it.next(), this.queue.poll());
        } // FOR
    }