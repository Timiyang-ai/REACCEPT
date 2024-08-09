@Test
    public void testBiasWithBaitCounts() {
        /* generate a read count collection with no fully uncovered targets */
        final int numTargets = RANDOM_TARGETS_WITH_BAIT_COUNTS.size();
        final RealMatrix readCountsMatrix = new BlockRealMatrix(numTargets, NUMBER_OF_RANDOM_SAMPLES);
        final double[] data = new double[numTargets];
        Arrays.fill(data, 1.0);
        for (int si = 0; si < NUMBER_OF_RANDOM_SAMPLES; si++) {
            readCountsMatrix.setColumn(si, data);
        }
        final ReadCountCollection readCountCollection = new ReadCountCollection(RANDOM_TARGETS_WITH_BAIT_COUNTS,
                RANDOM_READ_COUNTS_WITH_BAIT_BIAS.columnNames(), readCountsMatrix);
        final TargetCoverageSexGenotypeCalculator sexGenotypeCalculator = new TargetCoverageSexGenotypeCalculator(
                readCountCollection, RANDOM_TARGETS_WITH_BAIT_COUNTS, HOMO_SAPIENS_GERMLINE_CONTIG_PLOIDY_ANNOTATIONS,
                null, MAPPING_ERROR_PROBABILITY);

        /* generate expected biases */
        final double[] expectedAutosomalBiases = sexGenotypeCalculator.getAutosomalTargetList().stream()
                .mapToDouble(target -> target.getAnnotations().getDouble(TargetAnnotation.BAIT_COUNT))
                .toArray();
        final double[] expectedAllosomalBiases = sexGenotypeCalculator.getAllosomalTargetList().stream()
                .mapToDouble(target -> target.getAnnotations().getDouble(TargetAnnotation.BAIT_COUNT))
                .toArray();
        final double meanBias = (Arrays.stream(expectedAutosomalBiases).sum() + Arrays.stream(expectedAllosomalBiases).sum()) /
                (expectedAutosomalBiases.length + expectedAllosomalBiases.length);

        IntStream.range(0, expectedAutosomalBiases.length).forEach(ti -> expectedAutosomalBiases[ti] /= meanBias);
        IntStream.range(0, expectedAllosomalBiases.length).forEach(ti -> expectedAllosomalBiases[ti] /= meanBias);

        ArrayAsserts.assertArrayEquals(expectedAutosomalBiases, sexGenotypeCalculator.getAutosomalTargetBiases(), EPSILON);
        ArrayAsserts.assertArrayEquals(expectedAllosomalBiases, sexGenotypeCalculator.getAllosomalTargetBiases(), EPSILON);
    }