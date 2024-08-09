@Test
    public void testPackAlignments() throws Exception {

        //Retrieve data to pack
        String path = "http://www.broadinstitute.org/igvdata/1KG/pilot2Bams/NA12878.SLX.bam";
        String chr = "1";
        int start = 557000;
        int end = 558000;
        boolean contained = false;

        ResourceLocator rl = new ResourceLocator(path);
        AlignmentReader samReader = AlignmentReaderFactory.getReader(rl);
        CloseableIterator<Alignment> iter = samReader.query(chr, start, end, contained);
        ///////////////////////////

        boolean showDuplicates = false;
        int qualityThreshold = 0;
        int maxLevels = 1000;

        Map<String, List<AlignmentInterval.Row>> result = (new AlignmentPacker()).packAlignments(iter, end, false, new AlignmentTrack.RenderOptions(), 10000);
        assertEquals(1, result.size());
        for (List<AlignmentInterval.Row> alignmentrows : result.values()) {
            for (AlignmentInterval.Row alignmentrow : alignmentrows) {
                List<Alignment> alignments = alignmentrow.alignments;
                for (int ii = 1; ii < alignments.size(); ii++) {
                    assertTrue(alignments.get(ii).getAlignmentStart() > alignments.get(ii - 1).getAlignmentStart());
                    assertTrue(alignments.get(ii).getAlignmentStart() - alignments.get(ii - 1).getAlignmentEnd() >= AlignmentPacker.MIN_ALIGNMENT_SPACING);
                }
            }
        }


    }