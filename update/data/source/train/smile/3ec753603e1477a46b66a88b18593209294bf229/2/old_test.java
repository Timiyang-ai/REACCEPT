@Test
    public void testDfs() {
        System.out.println("dfs sort");
        int[] ts = {1,10,12,11,9,4,5,3,2,6,0,7,8};

        Graph graph = new AdjacencyMatrix(13, true);
        graph.addEdge(8, 7);
        graph.addEdge(7, 6);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(0, 3);
        graph.addEdge(0, 5);
        graph.addEdge(0, 6);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);
        graph.addEdge(3, 5);
        graph.addEdge(6, 4);
        graph.addEdge(6, 9);
        graph.addEdge(4, 9);
        graph.addEdge(9, 10);
        graph.addEdge(9, 11);
        graph.addEdge(9, 12);
        graph.addEdge(11, 12);

        assertTrue(MathEx.equals(ts, graph.sortdfs()));
    }