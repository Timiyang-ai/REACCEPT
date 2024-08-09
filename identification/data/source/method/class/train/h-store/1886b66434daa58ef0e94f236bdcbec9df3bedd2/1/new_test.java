@Test
    public void testStartTransaction() throws Exception {
        long txn_id = XACT_ID++;
        State state = t_estimator.startTransaction(txn_id, this.catalog_proc, multip_trace.getParams());
        assertNotNull(state);
        MarkovEstimate est = state.getInitialEstimate();
        assertNotNull(est);
        assertNull(state.getLastEstimate());
        System.err.println(est.toString());
        
        MarkovGraph markov = markovs.get(BASE_PARTITION, this.catalog_proc);
        List<Vertex> initial_path = state.getInitialPath();
        assertFalse(initial_path.isEmpty());
        
        System.err.println("# of Vertices: " + markov.getVertexCount());
        System.err.println("# of Edges:    " + markov.getEdgeCount());
        System.err.println("Confidence:    " + String.format("%.4f", t_estimator.getConfidence(txn_id)));
        System.err.println("\nINITIAL PATH:\n" + StringUtil.join("\n", initial_path));
        
//        GraphvizExport<Vertex, Edge> gv = MarkovUtil.exportGraphviz(markov, false, null);
//        gv.highlightPath(markov.getPath(initial_path), "blue");
//        gv.writeToTempFile(this.catalog_proc, 0);
//
//        MarkovUtil.exportGraphviz(markov, false, markov.getPath(multip_path)).writeToTempFile(this.catalog_proc, 1);
        
        assertFalse(est.isSinglePartition(this.thresholds));
        assertFalse(est.isAbortable(this.thresholds));
        
        Set<Integer> partitions = p_estimator.getAllPartitions(multip_trace);
        
        for (Integer partition : ALL_PARTITIONS) {
            if (partitions.contains(partition)) { //  == BASE_PARTITION) {
                assertFalse("isFinishedPartition(" + partition + ")", est.isFinishedPartition(thresholds, partition));
                assertTrue("isWritePartition(" + partition + ")", est.isWritePartition(thresholds, partition) == true);
                assertTrue("isTargetPartition(" + partition + ")", est.isTargetPartition(thresholds, partition) == true);
            } else {
                assertTrue("isFinishedPartition(" + partition + ")", est.isFinishedPartition(thresholds, partition));
                assertFalse("isWritePartition(" + partition + ")", est.isWritePartition(thresholds, partition) == true);
                assertFalse("isTargetPartition(" + partition + ")", est.isTargetPartition(thresholds, partition) == true);
            }
        } // FOR
    }