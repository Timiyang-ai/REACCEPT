@Test
    public void testPackAlignments() throws Exception {

        ///////////////////////////
        /*
        boolean showDuplicates = false;
        int qualityThreshold = 0;
        int maxLevels = 1000;
        */
        AlignmentInterval interval = getAlignmentInterval();

        Map<String, List<Row>> result = (new AlignmentPacker()).packAlignments(Arrays.asList(interval),
                new AlignmentTrack.RenderOptions());
        assertEquals(1, result.size());
        for (List<Row> alignmentrows : result.values()) {
            for (Row alignmentrow : alignmentrows) {
                List<Alignment> alignments = alignmentrow.alignments;
                for (int ii = 1; ii < alignments.size(); ii++) {
                    assertTrue(alignments.get(ii).getAlignmentStart() > alignments.get(ii - 1).getAlignmentStart());
                    assertTrue(alignments.get(ii).getAlignmentStart() - alignments.get(ii - 1).getAlignmentEnd() >= AlignmentPacker.MIN_ALIGNMENT_SPACING);
                }
            }
        }


    }