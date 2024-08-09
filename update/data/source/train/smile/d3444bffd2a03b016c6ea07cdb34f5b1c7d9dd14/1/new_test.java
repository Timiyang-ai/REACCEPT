@Test
    public void testTTree() {
        System.out.println("T-Tree");

        FPTree tree = FPTree.build(3, itemsets);
        TotalSupportTree ttree = new TotalSupportTree(tree);

        int[][] items = {
            {3, 2, 1},
            {3},
            {3, 1},
            {3, 2},
            {4},
            {2}
        };
        
        assertEquals(3, ttree.getSupport(items[0]));
        assertEquals(8, ttree.getSupport(items[1]));
        assertEquals(5, ttree.getSupport(items[2]));
        assertEquals(6, ttree.getSupport(items[3]));
        assertEquals(3, ttree.getSupport(items[4]));
        assertEquals(7, ttree.getSupport(items[5]));

        List<ItemSet> results = ttree.stream().collect(Collectors.toList());
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