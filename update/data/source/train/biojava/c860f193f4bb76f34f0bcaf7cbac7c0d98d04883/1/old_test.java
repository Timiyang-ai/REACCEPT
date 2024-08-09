@Test
	public void testProcess() throws Exception {

		logger.info("process protein");
		InputStream inStream = this.getClass().getResourceAsStream("/BondFeature.gb");
		assertNotNull(inStream);
		
		GenbankReader<ProteinSequence,AminoAcidCompound> GenbankProtein = 
				new GenbankReader<ProteinSequence,AminoAcidCompound>(
						inStream, 
						new GenericGenbankHeaderParser<ProteinSequence,AminoAcidCompound>(), 
						new ProteinSequenceCreator(AminoAcidCompoundSet.getAminoAcidCompoundSet())
						);
		@SuppressWarnings("unused")
		LinkedHashMap<String,ProteinSequence> proteinSequences = GenbankProtein.process();
		inStream.close();

		logger.info("process DNA");
		inStream = this.getClass().getResourceAsStream("/NM_000266.gb");
		assertNotNull(inStream);

		GenbankReader<DNASequence,NucleotideCompound> GenbankDNA = 
				new GenbankReader<DNASequence,NucleotideCompound>(
						inStream,
						new GenericGenbankHeaderParser<DNASequence,NucleotideCompound>(), 
						new DNASequenceCreator(DNACompoundSet.getDNACompoundSet())
						);
		@SuppressWarnings("unused")
		LinkedHashMap<String,DNASequence> dnaSequences = GenbankDNA.process();
		inStream.close();
	}