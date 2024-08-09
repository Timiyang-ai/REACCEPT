    private static RTree<Object, Rectangle> create(int maxChildren, int n) {
        RTree<Object, Rectangle> tree = RTree.maxChildren(maxChildren).create();
        for (int i = 1; i <= n; i++)
            tree = tree.add(e(i));
        return tree;
    }