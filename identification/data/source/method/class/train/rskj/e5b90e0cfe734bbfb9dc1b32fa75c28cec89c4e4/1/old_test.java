    @Test
    public void process() {
        class Indexer {
            public int index = 0;
        }

        Indexer idx = new Indexer();
        queue.process(30, entry -> {
            Assert.assertEquals(entry, queueEntries.get(idx.index));
            return idx.index++ % 2 == 0;
        });
        Assert.assertEquals(5, idx.index);
        Assert.assertEquals(Arrays.asList(
            new ReleaseRequestQueue.Entry(mockAddress(5), Coin.COIN),
            new ReleaseRequestQueue.Entry(mockAddress(3), Coin.MILLICOIN)
        ), queue.getEntries());
    }