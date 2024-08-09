@Override
    protected void setup(final SAMFileHeader header, final File samFile) {
        IOUtil.assertFileIsWritable(CHART_OUTPUT);

        if (SUMMARY_OUTPUT != null) IOUtil.assertFileIsWritable(SUMMARY_OUTPUT);

        IOUtil.assertFileIsReadable(REFERENCE_SEQUENCE);

        final ReferenceSequenceFile refFile = ReferenceSequenceFileFactory.getReferenceSequenceFile(REFERENCE_SEQUENCE);
        ReferenceSequence ref;

        while ((ref = refFile.nextSequence()) != null) {
            final byte[] refBases = ref.getBases();
            final String refName = ref.getName();
            StringUtil.toUpperCase(refBases);
            final int refLength = refBases.length;
            final int lastWindowStart = refLength - windowSize;
            final byte[] gc = calculateAllGcs(refBases, windowsByGc, lastWindowStart);
            gcByRef.put(refName, gc);
        }
        //Delegate actual collection to GcBiasMetricCollector
        multiCollector = new GcBiasMetricsCollector(METRIC_ACCUMULATION_LEVEL, gcByRef, windowsByGc, header.getReadGroups(), windowSize, IS_BISULFITE_SEQUENCED);
    }