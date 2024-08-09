@Test
	public void testProcess() throws Exception {

		logger.info("process protein");
		InputStream inStream = this.getClass().getResourceAsStream("/BondFeature.gb");
		assertNotNull(inStream);

		GenbankReader<ProteinSequence, AminoAcidCompound> genbankProtein
				= new GenbankReader<>(
						inStream,
						new GenericGenbankHeaderParser<>(),
						new ProteinSequenceCreator(AminoAcidCompoundSet.getAminoAcidCompoundSet())
				);

		LinkedHashMap<String, ProteinSequence> proteinSequences = genbankProtein.process();

		assertNotNull(proteinSequences);
		assertEquals(1, proteinSequences.size());

		ProteinSequence proteinSequence = proteinSequences.get("NP_000257");
		assertNotNull(proteinSequences.get("NP_000257"));
		assertEquals("NP_000257", proteinSequence.getAccession().getID());
		assertEquals("4557789", proteinSequence.getAccession().getIdentifier());
		assertEquals("GENBANK", proteinSequence.getAccession().getDataSource().name());
		assertEquals(1, proteinSequence.getAccession().getVersion().intValue());
		assertTrue(genbankProtein.isClosed());

		logger.info("process DNA");
		inStream = this.getClass().getResourceAsStream("/NM_000266.gb");
		assertNotNull(inStream);

		GenbankReader<DNASequence, NucleotideCompound> genbankDNA
				= new GenbankReader<>(
						inStream,
						new GenericGenbankHeaderParser<>(),
						new DNASequenceCreator(DNACompoundSet.getDNACompoundSet())
				);
		LinkedHashMap<String, DNASequence> dnaSequences = genbankDNA.process();

		assertNotNull(dnaSequences);
		assertEquals(1, dnaSequences.size());

		DNASequence dnaSequence = dnaSequences.get("NM_000266");
		assertNotNull(dnaSequences.get("NM_000266"));
		assertEquals("NM_000266", dnaSequence.getAccession().getID());
		assertEquals("223671892", dnaSequence.getAccession().getIdentifier());
		assertEquals("GENBANK", dnaSequence.getAccession().getDataSource().name());
		assertEquals(3, dnaSequence.getAccession().getVersion().intValue());
		assertTrue(genbankDNA.isClosed());
	}