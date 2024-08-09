@Test
    public void testCreateObjects() throws Exception {
        System.out.println("createObjects");
        
        String resource = "/org/biojava/nbio/core/search/io/blast/testBlastReport.xml";
        URL resourceURL = getClass().getResource(resource);
        File file = new File(resourceURL.getFile());
        
        BlastXMLQuery instance = new BlastXMLQuery();
        instance.setFile(file);
        
        //instance.setQueryReferences(null);
        //instance.setDatabaseReferences(null);
        ArrayList<Result> result = instance.createObjects(1e-10);
        
        // test with random manual selected results
        BlastHsp hsp1hit1res1 = new BlastHspBuilder()
                .setHspNum(1)
                .setHspBitScore(377.211)
                .setHspEvalue(8.04143e-093)
                .setHspQueryFrom(1)
                .setHspQueryTo(224)
                .setHspHitFrom(1035)
                .setHspHitTo(811)
                .setHspQueryFrame(-1)
                .setHspIdentity(213)
                .setHspPositive(213)
                .setHspGaps(5)
                .setHspAlignLen(227)
                .setHspQseq("CTGACGACAGCCATGCACCACCTGTCTCGACTTTCCCCCGAAGGGCACCTAATGTATCTCTACCTCGTTAGTCGGATGTCAAGACCTGGTAAGGTTTTTTCGCGTATCTTCGAATTAAACCACATACTCCACTGCTTGTGCGG-CCCCCGTCAATTCCTTTGAGTTTCAACCTTGCGGCCGTACTCCC-AGGTGGA-TACTTATTGTGTTAACTCCGGCACGGAAGG")
                .setHspHseq("CTGACGACAACCATGCACCACCTGTCTCAACTTTCCCC-GAAGGGCACCTAATGTATCTCTACTTCGTTAGTTGGATGTCAAGACCTGGTAAGGTT-CTTCGCGTTGCTTCGAATTAAACCACATACTCCACTGCTTGTGCGGGCCCCCGTCAATTCCTTTGAGTTTCAACCTTGCGGTCGTACTCCCCAGGTGGATTACTTATTGTGTTAACTCCGGCACAGAAGG")
                .setHspIdentityString("||||||||| |||||||||||||||||| ||||||||| |||||||||||||||||||||||| |||||||| |||||||||||||||||||||||  |||||||  |||||||||||||||||||||||||||||||||||| |||||||||||||||||||||||||||||||||| ||||||||| ||||||| |||||||||||||||||||||||| |||||")
                .createBlastHsp();
        BlastHit hit1res1 = new BlastHitBuilder()
                .setHitNum(1)
                .setHitId("gnl|BL_ORD_ID|2006")
                .setHitDef("gi|265679047|ref|NR_029355.1| Clostridium methylpentosum DSM 5476 strain R2 16S ribosomal RNA, partial sequence")
                .setHitAccession("2006")
                .setHitLen(1435)
                .createBlastHit();
        
        BlastResult res1 = new BlastResultBuilder()
                .setProgram("blastn")
                .setVersion("BLASTN 2.2.29+")
                .setReference("Zheng Zhang, Scott Schwartz, Lukas Wagner, and Webb Miller (2000), &quot;A greedy algorithm for aligning DNA sequences&quot;, J Comput Biol 2000; 7(1-2):203-14.")
                .setQueryID("Query_1")
                .setQueryDef("42DKN:00022:00047")
                .setQueryLength(226)
                .createBlastResult();
        
        Result expRes1 = result.get(0);
        Hit expHit1res1 = expRes1.iterator().next();
        Hsp expHsp1hit1res1 = expHit1res1.iterator().next();
        
        // result not testable without all hits and hsp
        //assertEquals(expRes1, res1);
        
        // hit test
        assertEquals(expHit1res1, hit1res1);
        
        // hsp test
        assertEquals(expHsp1hit1res1, hsp1hit1res1);
    }