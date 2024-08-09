    @Test
    public void test_getOutgoingEdges() {
        final TestNode node = new TestNode("node");
        final TestNode edge = new TestNode("edge");
        mGraph.addNode(node);
        mGraph.addNode(edge);
        mGraph.addEdge(node, edge);

        // Now assert the getOutgoingEdges returns a list which has one element (node)
        final List<TestNode> outgoingEdges = mGraph.getOutgoingEdges(edge);
        assertNotNull(outgoingEdges);
        assertEquals(1, outgoingEdges.size());
        assertTrue(outgoingEdges.contains(node));
    }