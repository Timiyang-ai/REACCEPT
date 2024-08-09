@Test
    public void testProcess() throws Exception {
        System.out.println("process");
        InputStream inStream = this.getClass().getResourceAsStream("/PF00104_small.fasta");
        assertNotNull(inStream);


        FastaReader<ProteinSequence> fastaReader = new FastaReader<ProteinSequence>(inStream, new GenericFastaHeaderParser(), new ProteinSequenceCreator(AminoAcidCompoundSet.getAminoAcidCompoundSet()));
        List<ProteinSequence> proteinSequences = fastaReader.process();
        inStream.close();


        //Should have 282 sequences
        System.out.println("Expecting 283 got " + proteinSequences.size());
        assertEquals(proteinSequences.size() ,  283 );

        ProteinSequence proteinSequence = proteinSequences.get(0);
        assertEquals(proteinSequence.getAccession().getID(),"A2D504_ATEGE/1-46");
        assertEquals(proteinSequence.getSequenceAsString(),"-----------------FK-N----LP-LED----------------Q----ITL--IQY-----------SWM----------------------CL-SSFA------LSWRSYK---HTNSQFLYFAPDLVF-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

        proteinSequence = proteinSequences.get(281);
        System.out.println(proteinSequence.getAccession());
        System.out.println(proteinSequence.getSequenceAsString());
        assertEquals(proteinSequence.getAccession().getID(),"Q9PU76_CRONI/141-323");
        assertEquals(proteinSequence.getSequenceAsString(),"VETVTELTEFAKSI-PGFS-N----LD-LND----------------Q----VTL--LKY-----------GVY----------------------EA-IFAM------LASVMNK---DGMPVAYGNGFITRE------------------------------------------------------------------------------------------------------------------------------------------------------------FLKSLRKPFCDIMEPKFDFA-MKF-NSL-E-LDDSDI--------------------SLFVA-AIIC-CGDRPG-------------------------------------------LVNV--GHIEKMQESIVHVLKL-H-----LQN---------NH---PD----------------------------DI------F--------LFP-KLLQKMAD-LRQLV-----------------TEH-AQLV--QIIKK---TESDAHLHPLL-------QEI---");

        proteinSequence = proteinSequences.get(282);
        assertEquals(proteinSequence.getAccession().getID(),"Q98SJ1_CHICK/15-61");
        assertEquals(proteinSequence.getSequenceAsString(),"---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------Q-----------------NW------Q--------RFY-QLTKLLDS-MHDVV-----------------ENL-LSFC--FQTFLDKSM--SIEFPEML-------AEI---");

        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }