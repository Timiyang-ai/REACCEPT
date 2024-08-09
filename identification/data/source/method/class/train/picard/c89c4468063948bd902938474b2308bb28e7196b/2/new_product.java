@Override
    protected void setup(final SAMFileHeader header, final File samFile) {
        IOUtil.assertFileIsWritable(CHART_OUTPUT);
        IOUtil.assertFileIsWritable(SUMMARY_OUTPUT);
        IOUtil.assertFileIsReadable(REFERENCE_SEQUENCE);

        //Calculate windowsByGc for the reference sequence
        final int[] windowsByGc = GcBiasUtils.calculateRefWindowsByGc(BINS, REFERENCE_SEQUENCE, SCAN_WINDOW_SIZE);

        //Delegate actual collection to GcBiasMetricCollector
        multiCollector = new GcBiasMetricsCollector(METRIC_ACCUMULATION_LEVEL, windowsByGc, header.getReadGroups(), SCAN_WINDOW_SIZE, IS_BISULFITE_SEQUENCED, ALSO_IGNORE_DUPLICATES);
    }