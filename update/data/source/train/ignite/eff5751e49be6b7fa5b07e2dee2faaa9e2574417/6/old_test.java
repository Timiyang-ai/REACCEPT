@Test
    public void testSplit() {
        Map<Double, Integer> lblMapping = new HashMap<>();
        lblMapping.put(1.0, 0);
        lblMapping.put(2.0, 1);

        GiniHistogram catFeatureSmpl1 = new GiniHistogram(0, lblMapping, feature1Meta);
        GiniHistogram contFeatureSmpl1 = new GiniHistogram(0, lblMapping, feature2Meta);
        GiniHistogram emptyHist = new GiniHistogram(0, lblMapping, feature3Meta);
        GiniHistogram catFeatureSmpl2 = new GiniHistogram(0, lblMapping, feature3Meta);

        feature2Meta.setMinVal(-5);
        feature2Meta.setBucketSize(1);

        for (BootstrappedVector vec : toSplitDataset) {
            catFeatureSmpl1.addElement(vec);
            contFeatureSmpl1.addElement(vec);
            catFeatureSmpl2.addElement(vec);
        }

        NodeSplit catSplit = catFeatureSmpl1.findBestSplit().get();
        NodeSplit contSplit = contFeatureSmpl1.findBestSplit().get();
        assertEquals(1.0, catSplit.getValue(), 0.01);
        assertEquals(-0.5, contSplit.getValue(), 0.01);
        assertFalse(emptyHist.findBestSplit().isPresent());
        assertFalse(catFeatureSmpl2.findBestSplit().isPresent());
    }