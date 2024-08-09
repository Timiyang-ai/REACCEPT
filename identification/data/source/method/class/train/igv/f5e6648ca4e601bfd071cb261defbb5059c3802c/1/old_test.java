@Test
    public void testPackAlignments() {
        /*
        String path = "/Users/jrobinso/IGV/Alignments/303KY.8.paired.bam";
        String serverURL = "http://localhost:8080/webservices/igv";
        String chr = "chr7";
        int start = 2244149;
        int end = 2250144;
         * */

        String path = "http://www.broadinstitute.org/igvdata/1KG/pilot2Bams/NA12878.SLX.bam";
        String chr = "1";
        int start = 557000;  //98751004; //
        int end = 558000; //98751046; //
        boolean contained = false;

        ResourceLocator rl = new ResourceLocator(path);

        BAMRemoteQueryReader bamReader = new BAMRemoteQueryReader(rl);
        CloseableIterator<Alignment> iter = bamReader.query(chr, start, end, contained);
        boolean showDuplicates = false;
        int qualityThreshold = 0;
        int maxLevels = 1000;


        Map<String, List<AlignmentInterval.Row>> result = (new AlignmentPacker()).packAlignments(iter, end, false, null, 10000);

    }