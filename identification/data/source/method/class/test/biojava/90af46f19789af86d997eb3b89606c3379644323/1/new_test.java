@Test
	public void testProcess() throws Exception {
		logger.info("process");
		InputStream inStream = this.getClass().getResourceAsStream("/PF00104_small.fasta");
		assertNotNull(inStream);


		FastaReader<ProteinSequence,AminoAcidCompound> fastaReader = new FastaReader<ProteinSequence,AminoAcidCompound>(inStream, new GenericFastaHeaderParser<ProteinSequence,AminoAcidCompound>(), new ProteinSequenceCreator(AminoAcidCompoundSet.getAminoAcidCompoundSet()));
		LinkedHashMap<String,ProteinSequence> proteinSequences = fastaReader.process();
		inStream.close();

		//Should have 282 sequences
		//logger.debug("Expecting 283 got " + proteinSequences.size());
		assertEquals(proteinSequences.size() ,  283 );

		int seqNum = 0;
		for(String id:proteinSequences.keySet()) {
			ProteinSequence proteinSequence = proteinSequences.get(id);
			switch(seqNum) {
				case 0:
					assertEquals(proteinSequence.getAccession().getID(),"A2D504_ATEGE/1-46");
					assertEquals(proteinSequence.getSequenceAsString(),"-----------------FK-N----LP-LED----------------Q----ITL--IQY-----------SWM----------------------CL-SSFA------LSWRSYK---HTNSQFLYFAPDLVF-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
					break;
				case 281:
					//logger.debug("Get Accession: {}", proteinSequence.getAccession());
					//logger.debug("Get Protein Sequence: {}", proteinSequence.getSequenceAsString());
					assertEquals(proteinSequence.getAccession().getID(),"Q9PU76_CRONI/141-323");
					assertEquals(proteinSequence.getSequenceAsString(),"VETVTELTEFAKSI-PGFS-N----LD-LND----------------Q----VTL--LKY-----------GVY----------------------EA-IFAM------LASVMNK---DGMPVAYGNGFITRE------------------------------------------------------------------------------------------------------------------------------------------------------------FLKSLRKPFCDIMEPKFDFA-MKF-NSL-E-LDDSDI--------------------SLFVA-AIIC-CGDRPG-------------------------------------------LVNV--GHIEKMQESIVHVLKL-H-----LQN---------NH---PD----------------------------DI------F--------LFP-KLLQKMAD-LRQLV-----------------TEH-AQLV--QIIKK---TESDAHLHPLL-------QEI---");
					break;
				case 282:
					assertEquals(proteinSequence.getAccession().getID(),"Q98SJ1_CHICK/15-61");
					assertEquals(proteinSequence.getSequenceAsString(),"---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------Q-----------------NW------Q--------RFY-QLTKLLDS-MHDVV-----------------ENL-LSFC--FQTFLDKSM--SIEFPEML-------AEI---");
					break;
			}
			seqNum++;
		}
		assertEquals(seqNum,283);
	}