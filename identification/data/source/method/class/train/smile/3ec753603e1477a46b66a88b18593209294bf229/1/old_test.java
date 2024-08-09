@Test
    public void testBfs2() {
        System.out.println("bfs connected component");
        int[] size = {3, 5};
        int[] id = {0, 1, 0, 1, 1, 1, 0, 1};
        int[][] cc = {{0, 2, 6}, {1, 3, 4, 5, 7}};

        Graph graph = new AdjacencyList(8);
        graph.addEdge(0, 2);
        graph.addEdge(1, 7);
        graph.addEdge(2, 6);
        graph.addEdge(7, 4);
        graph.addEdge(3, 4);
        graph.addEdge(3, 5);
        graph.addEdge(5, 4);

        int[][] cc2 = graph.bfs();
        assertTrue(MathEx.equals(cc, cc2));
    }