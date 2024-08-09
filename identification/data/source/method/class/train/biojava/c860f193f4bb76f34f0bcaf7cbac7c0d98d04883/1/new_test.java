@Test
	public void testProcess() throws Exception {

		logger.info("process protein");
		InputStream inStream = this.getClass().getResourceAsStream("/BondFeature.gb");
		assertNotNull(inStream);
		
		GenbankReader<ProteinSequence,AminoAcidCompound> genbankProtein = 
				new GenbankReader<ProteinSequence,AminoAcidCompound>(
						inStream, 
						new GenericGenbankHeaderParser<ProteinSequence,AminoAcidCompound>(), 
						new ProteinSequenceCreator(AminoAcidCompoundSet.getAminoAcidCompoundSet())
						);
		@SuppressWarnings("unused")
		LinkedHashMap<String,ProteinSequence> proteinSequences = genbankProtein.process();
		inStream.close();

		logger.info("process DNA");
		inStream = this.getClass().getResourceAsStream("/NM_000266.gb");
		assertNotNull(inStream);

		GenbankReader<DNASequence,NucleotideCompound> genbankDNA = 
				new GenbankReader<DNASequence,NucleotideCompound>(
						inStream,
						new GenericGenbankHeaderParser<DNASequence,NucleotideCompound>(), 
						new DNASequenceCreator(DNACompoundSet.getDNACompoundSet())
						);
		@SuppressWarnings("unused")
		LinkedHashMap<String,DNASequence> oneDNASequence = genbankDNA.process();
		inStream.close();

		logger.info("process 2 DNAs");
		inStream = this.getClass().getResourceAsStream("/two-dnaseqs.gb");
		assertNotNull(inStream);

		GenbankReader<DNASequence,NucleotideCompound> readTwoDNAs = 
				new GenbankReader<DNASequence,NucleotideCompound>(
						inStream,
						new GenericGenbankHeaderParser<DNASequence,NucleotideCompound>(), 
						new DNASequenceCreator(DNACompoundSet.getDNACompoundSet())
						);
		@SuppressWarnings("unused")
		LinkedHashMap<String,DNASequence> twoDNAs = readTwoDNAs.process();
		inStream.close();

		logger.info("process coli genome");
		inStream = this.getClass().getResourceAsStream("/NC_000913.gb");
		assertNotNull(inStream);

		
		GenbankReader<DNASequence,NucleotideCompound> readColiGenome = 
				new GenbankReader<DNASequence,NucleotideCompound>(
						inStream,
						new GenericGenbankHeaderParser<DNASequence,NucleotideCompound>(), 
						new DNASequenceCreator(DNACompoundSet.getDNACompoundSet())
						);
		@SuppressWarnings("unused")
		LinkedHashMap<String,DNASequence> coliGenome = readColiGenome.process();
		assertNotNull(inStream);
		inStream.close();	
	}