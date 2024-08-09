@Test
	public void testProcess() throws Exception {

		System.out.println("process protein");
		InputStream inStream = this.getClass().getResourceAsStream("/BondFeature.gb");
		assertNotNull(inStream);
		
		GenbankReader<ProteinSequence,AminoAcidCompound> GenbankProtein = 
				new GenbankReader<ProteinSequence,AminoAcidCompound>(
						inStream, 
						new GenericGenbankHeaderParser<ProteinSequence,AminoAcidCompound>(), 
						new ProteinSequenceCreator(AminoAcidCompoundSet.getAminoAcidCompoundSet())
						);
		LinkedHashMap<String,ProteinSequence> proteinSequences = GenbankProtein.process();
		inStream.close();

		System.out.println("process DNA");
		inStream = this.getClass().getResourceAsStream("/NM_000266.gb");
		assertNotNull(inStream);

		GenbankReader<DNASequence,NucleotideCompound> GenbankDNA = 
				new GenbankReader<DNASequence,NucleotideCompound>(
						inStream,
						new GenericGenbankHeaderParser<DNASequence,NucleotideCompound>(), 
						new DNASequenceCreator(DNACompoundSet.getDNACompoundSet())
						);
		LinkedHashMap<String,DNASequence> dnaSequences = GenbankDNA.process();
		inStream.close();
	}