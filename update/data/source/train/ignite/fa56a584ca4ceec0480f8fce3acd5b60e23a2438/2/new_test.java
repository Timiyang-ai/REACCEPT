@Test
    public void testFit() {
        int size = 100;

        Map<Integer, double[]> data = new HashMap<>();

        Random rnd = new Random(0);
        for (int i = 0; i < size; i++) {
            double x = rnd.nextDouble() - 0.5;
            data.put(i, new double[]{x, x > 0 ? 1 : 0});
        }

        DecisionTreeRegressionTrainer trainer = new DecisionTreeRegressionTrainer(1, 0);

        DecisionTreeNode tree = trainer.fit(
            data,
            parts,
            (k, v) -> VectorUtils.of(Arrays.copyOf(v, v.length - 1)),
            (k, v) -> v[v.length - 1]
        );

        assertTrue(tree instanceof DecisionTreeConditionalNode);

        DecisionTreeConditionalNode node = (DecisionTreeConditionalNode) tree;

        assertEquals(0, node.getThreshold(), 1e-3);

        assertTrue(node.getThenNode() instanceof DecisionTreeLeafNode);
        assertTrue(node.getElseNode() instanceof DecisionTreeLeafNode);

        DecisionTreeLeafNode thenNode = (DecisionTreeLeafNode) node.getThenNode();
        DecisionTreeLeafNode elseNode = (DecisionTreeLeafNode) node.getElseNode();

        assertEquals(1, thenNode.getVal(), 1e-10);
        assertEquals(0, elseNode.getVal(), 1e-10);
    }