@Test
	public void testMergeSequence() throws CompoundNotFoundException {

		// Create an Atom Array of ploy-alanine
		List<Atom> atoms = new ArrayList<Atom>(100);
		for (int i = 0; i < 100; i++) {
			Group g = new AminoAcidImpl();
			g.setPDBName("ALA");
			Atom a = new AtomImpl();
			a.setName(StructureTools.CA_ATOM_NAME);
			g.addAtom(a);
			atoms.add(a);
		}
		Atom[] reprAtoms = atoms.toArray(new Atom[atoms.size()]);

		// Create two identical SubunitCluster
		SubunitCluster sc1 = new SubunitCluster(new Subunit(reprAtoms,
				"subunit 1", null, null));
		SubunitCluster sc2 = new SubunitCluster(new Subunit(reprAtoms,
				"subunit 2", null, null));
		SubunitClustererParameters clustererParameters = new SubunitClustererParameters();
		clustererParameters.setSequenceIdentityThreshold(0.9);
		clustererParameters.setSequenceCoverageThreshold(0.9);
		boolean merged = sc1.mergeSequence(sc2, clustererParameters);

		// Merged have to be true, and the merged SubunitCluster is sc1
		assertTrue(merged);
		assertEquals(sc1.size(), 2);
		assertEquals(sc2.size(), 1);
		assertEquals(sc1.length(), 100);

		// Create an Atom Array of poly-glycine
		List<Atom> atoms2 = new ArrayList<Atom>(100);
		for (int i = 0; i < 100; i++) {
			Group g = new AminoAcidImpl();
			g.setPDBName("GLY");
			Atom a = new AtomImpl();
			a.setName(StructureTools.CA_ATOM_NAME);
			g.addAtom(a);
			atoms2.add(a);
		}
		Atom[] reprAtoms2 = atoms2.toArray(new Atom[atoms2.size()]);

		SubunitCluster sc3 = new SubunitCluster(new Subunit(reprAtoms2,
				"subunit 3", null, null));

		merged = sc1.mergeSequence(sc3,clustererParameters);

		// Merged have to be false, and Clusters result inmodified
		assertFalse(merged);
		assertEquals(sc1.size(), 2);
		assertEquals(sc2.size(), 1);
		assertEquals(sc1.length(), 100);

		// Create an Atom Array of 9 glycine and 91 alanine
		List<Atom> atoms3 = new ArrayList<Atom>(100);
		for (int i = 0; i < 9; i++) {
			Group g = new AminoAcidImpl();
			g.setPDBName("GLY");
			Atom a = new AtomImpl();
			a.setName(StructureTools.CA_ATOM_NAME);
			g.addAtom(a);
			atoms3.add(a);
		}
		for (int i = 0; i < 91; i++) {
			Group g = new AminoAcidImpl();
			g.setPDBName("ALA");
			Atom a = new AtomImpl();
			a.setName(StructureTools.CA_ATOM_NAME);
			g.addAtom(a);
			atoms3.add(a);
		}
		Atom[] reprAtoms3 = atoms3.toArray(new Atom[atoms3.size()]);

		SubunitCluster sc4 = new SubunitCluster(new Subunit(reprAtoms3,
				"subunit 4", null, null));

		merged = sc1.mergeSequence(sc4, clustererParameters);

		// Merged have to be true, and the merged SubunitCluster is sc1
		assertTrue(merged);
		assertEquals(sc1.size(), 3);
		assertEquals(sc2.size(), 1);
		assertEquals(sc1.length(), 91);

	}