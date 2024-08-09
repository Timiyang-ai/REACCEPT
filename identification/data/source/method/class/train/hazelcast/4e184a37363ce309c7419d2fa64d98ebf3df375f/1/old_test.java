    @Test
    public void optimize() throws Exception {
        Selector selector = Selector.open();
        assumeTrue(findOptimizableSelectorClass(selector) != null);
        SelectorOptimizer.SelectionKeysSet keys = SelectorOptimizer.optimize(selector, logger);
        assertNotNull(keys);
    }