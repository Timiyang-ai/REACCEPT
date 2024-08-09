public void mergeSimilarSegments(final double sigmaThresholdSegmentMean,
                                     final double sigmaThresholdMinorAlleleFraction,
                                     final int numSamplesCopyRatio, final int numBurnInCopyRatio,
                                     final int numSamplesAlleleFraction, final int numBurnInAlleleFraction) {
        logger.info("Starting similar-segment merging...");
        logger.info("Initial number of segments: " + segments.size());
        List<ACNVModeledSegment> mergedSegments = new ArrayList<>(segments);

        //write initial model fit to file
        final File initialModeledSegmentsFile = new File(outputPrefix + "-" + INITIAL_SEG_FILE_TAG + ".seg");
        SegmentUtils.writeACNVModeledSegmentFile(initialModeledSegmentsFile, mergedSegments, segmentedModel.getGenome());

        //perform iterations of similar-segment merging until all similar segments are merged
        int prevNumSegments;
        for (int numIterations = 1; numIterations <= MAX_SIMILAR_SEGMENT_MERGE_ITERATIONS; numIterations++) {
            logger.info("Similar-segment merging iteration: " + numIterations);
            logger.info("Number of segments before merging: " + mergedSegments.size());
            prevNumSegments = mergedSegments.size();
            mergedSegments =
                    SegmentMergeUtils.mergeSimilarSegments(segments,
                            sigmaThresholdSegmentMean, sigmaThresholdMinorAlleleFraction);
            logger.info("Number of segments after merging: " + mergedSegments.size());
            segmentedModel = new SegmentedModel(toUnmodeledSegments(mergedSegments), segmentedModel.getGenome());
            if (mergedSegments.size() == prevNumSegments) {
                break;
            }
            //refit model and write to file after each iteration
            fitModel(numSamplesCopyRatio, numBurnInCopyRatio, numSamplesAlleleFraction, numBurnInAlleleFraction);
            final File modeledSegmentsFile = new File(outputPrefix + "-" + INTERMEDIATE_SEG_FILE_TAG + "-" + numIterations + ".seg");
            SegmentUtils.writeACNVModeledSegmentFile(modeledSegmentsFile, segments, segmentedModel.getGenome());
        }

        //perform final model fit and write to file
        fitModel(this.numSamplesCopyRatio, this.numBurnInCopyRatio, this.numSamplesAlleleFraction, this.numBurnInAlleleFraction);
        final File finalModeledSegmentsFile = new File(outputPrefix + "-" + FINAL_SEG_FILE_TAG + ".seg");
        SegmentUtils.writeACNVModeledSegmentFile(finalModeledSegmentsFile, segments, segmentedModel.getGenome());
    }