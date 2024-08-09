@Test
	public void testProcess() throws Exception {

		System.out.println("process");
		InputStream inStream = this.getClass().getResourceAsStream("/BondFeature.gb");
		assertNotNull(inStream);

		GenbankReader<ProteinSequence,AminoAcidCompound> GenbankReader = new GenbankReader<ProteinSequence,AminoAcidCompound>(inStream, new GenericGenbankHeaderParser<ProteinSequence,AminoAcidCompound>(), new ProteinSequenceCreator(AminoAcidCompoundSet.getAminoAcidCompoundSet()));
		LinkedHashMap<String,ProteinSequence> proteinSequences = GenbankReader.process();
		inStream.close();

	}