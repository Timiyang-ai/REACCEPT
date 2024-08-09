@Test
    public void testCountSmallSegments() {
        final Genome genome = new Genome(TARGET_FILE_SMALL_SEGMENT_MERGING, SNP_FILE_SMALL_SEGMENT_MERGING,
                SAMPLE_NAME);
        final SegmentedModel model = new SegmentedModel(SEGMENT_FILE_SMALL_SEGMENT_MERGING, genome);

        final int resultNumberOfSegmentsBeforeMerging = model.getSegments().size();
        final int resultNumberOfSmallSegmentsBeforeMerging =
                countSmallSegments(model, SMALL_SEGMENT_TARGET_NUMBER_THRESHOLD);
        final int expectedNumberOfSegmentsBeforeMerging = 21;
        final int expectedNumberOfSmallSegmentsBeforeMerging = 15;
        Assert.assertEquals(resultNumberOfSegmentsBeforeMerging, expectedNumberOfSegmentsBeforeMerging);
        Assert.assertEquals(resultNumberOfSmallSegmentsBeforeMerging, expectedNumberOfSmallSegmentsBeforeMerging);
    }