@Test
    public void testMap() {
        GmmPartitionData data = new GmmPartitionData(
            Arrays.asList(
                new LabeledVector<>(VectorUtils.of(1, 0), 0.),
                new LabeledVector<>(VectorUtils.of(0, 1), 0.),
                new LabeledVector<>(VectorUtils.of(1, 1), 0.)
            ),

            new double[][] {
                new double[] {0.5, 0.1},
                new double[] {1.0, 0.4},
                new double[] {0.3, 0.2}
            }
        );

        List<MeanWithClusterProbAggregator> res = MeanWithClusterProbAggregator.map(data);
        assertEquals(2, res.size());

        MeanWithClusterProbAggregator agg1 = res.get(0);
        assertEquals(0.6, agg1.clusterProb(), 1e-2);
        assertArrayEquals(new double[] {0.44, 0.72}, agg1.mean().asArray(), 1e-2);

        MeanWithClusterProbAggregator agg2 = res.get(1);
        assertEquals(0.23, agg2.clusterProb(), 1e-2);
        assertArrayEquals(new double[] {0.42, 0.85}, agg2.mean().asArray(), 1e-2);
    }