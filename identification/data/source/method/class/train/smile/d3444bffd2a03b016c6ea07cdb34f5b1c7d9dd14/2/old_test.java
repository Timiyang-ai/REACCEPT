@Test
    public void testGetFrequentItemsets_0args() {
        System.out.println("getFrequentItemsets");
        FPGrowth fpgrowth = new FPGrowth(itemsets, 3);
        TotalSupportTree ttree = fpgrowth.buildTotalSupportTree();
        List<ItemSet> results = ttree.getFrequentItemsets();
        assertEquals(8, results.size());
        
        assertEquals(8, results.get(0).support);
        assertEquals(1, results.get(0).items.length);
        assertEquals(3, results.get(0).items[0]);
        
        assertEquals(7, results.get(1).support);
        assertEquals(1, results.get(1).items.length);
        assertEquals(2, results.get(1).items[0]);
        
        assertEquals(3, results.get(6).support);
        assertEquals(3, results.get(6).items.length);
        assertEquals(3, results.get(6).items[0]);
        assertEquals(2, results.get(6).items[1]);
        assertEquals(1, results.get(6).items[2]);
        
        assertEquals(3, results.get(7).support);
        assertEquals(1, results.get(7).items.length);
        assertEquals(4, results.get(7).items[0]);
    }