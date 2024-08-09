@Test
    public void testMergeSimilarSegments() {
        final JavaSparkContext ctx = SparkContextFactory.getTestSparkContext();
        LoggingUtils.setLoggingLevel(Log.LogLevel.INFO);

        final String tempDir = publicTestDir + "similar-segment-test";
        final File tempDirFile = createTempDir(tempDir);

        //load data (coverages, SNP counts, and segments)
        final Genome genome = new Genome(COVERAGES_FILE, SNP_COUNTS_FILE, SAMPLE_NAME);
        final SegmentedModel segmentedModel = new SegmentedModel(SEGMENT_FILE, genome);

        //run MCMC
        final ACNVModeller modeller =
                new ACNVModeller(segmentedModel, tempDirFile.getAbsolutePath() + "/test",
                        NUM_SAMPLES, NUM_BURN_IN, NUM_SAMPLES, NUM_BURN_IN, ctx);
        modeller.mergeSimilarSegments(INTERVAL_THRESHOLD, INTERVAL_THRESHOLD,
                NUM_SAMPLES, NUM_BURN_IN, NUM_SAMPLES, NUM_BURN_IN);

        //check equality of segments
        final List<SimpleInterval> segmentsResult =
                SegmentUtils.readIntervalsFromSegmentFile(new File(tempDirFile.getAbsolutePath() +
                        "/test-" + ACNVModeller.FINAL_SEG_FILE_TAG + ".seg"));
        final List<SimpleInterval> segmentsTruth =
                SegmentUtils.readIntervalsFromSegmentFile(SEGMENTS_TRUTH_FILE);
        Assert.assertEquals(segmentsResult, segmentsTruth);
    }