@Test
    public void testEquals() {
        System.out.println("equals");
        Object o;
        o = new BlastHspBuilder()
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
        Hsp instance = hspImpl;
        
        assertEquals(o, instance);
        
        // example of Hsp retrieved from uncomplete report. The result is null, hance false
        // (Those HSP may come from a tabular format, for example)
        o = new BlastHspBuilder()
                .setPercentageIdentity(100.00/100)
                .setHspAlignLen(48)
                .setMismatchCount(0)
                .setHspGaps(0)
                .setHspQueryFrom(1)
                .setHspQueryTo(48)
                .setHspHitFrom(344)
                .setHspHitTo(391)
                .setHspEvalue(4e-19)
                .setHspBitScore(95.6)
                .createBlastHsp();
        
        // At now, check will return false as it could be not determined
        assertFalse(uncompleteHsp.equals(o));
    }