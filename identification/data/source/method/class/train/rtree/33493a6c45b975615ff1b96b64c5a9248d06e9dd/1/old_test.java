    @Test
    public void testBuilder1() {
        RTree<Object, Point> tree = RTree.minChildren(1).maxChildren(4)
                .selector(new SelectorMinimalAreaIncrease()).splitter(new SplitterQuadratic())
                .create();
        testBuiltTree(tree);
    }