@Test
	public void testDivideInternally() throws StructureException, IOException {

		Structure s = StructureIO.getStructure("4e3e");

		// Create a SubunitCluster for the chain
		SubunitCluster sc1 = new SubunitCluster(
				new Subunit(StructureTools.getRepresentativeAtomArray(s
						.getChainByIndex(0))));

		// Clusters should be merged by identity
		boolean divided = sc1.divideInternally(0.8, 3.0, 20);

		// Divided has to be true, and Subunit length shorter than half
		assertTrue(divided);
		assertEquals(sc1.size(), 2);
		assertTrue(sc1.length() < 178);
		assertEquals(sc1.getAlignedAtomsSubunit(0).length,
				sc1.getAlignedAtomsSubunit(1).length);
	}