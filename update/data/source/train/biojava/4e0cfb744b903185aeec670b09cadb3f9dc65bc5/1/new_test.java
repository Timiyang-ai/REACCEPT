@Test
	public void testGetStructureInfo() {
		Structure structure = new StructureImpl();
		Chain chain = new ChainImpl();
		chain.setId("A");
		Map<String,Integer> testMap = new HashMap<>();
		testMap.put("A", 0);
		List<Chain> chainList = new ArrayList<>();
		chainList.add(chain);
		Group group = new AminoAcidImpl();
		chain.addGroup(group);
		Atom atomOne = new AtomImpl();
		Atom atomTwo = new AtomImpl();
		List<Atom> atomList = new ArrayList<>();
		atomList.add(atomOne);
		atomList.add(atomTwo);
		new BondImpl(atomOne, atomTwo, 1);
		structure.addChain(chain);
		group.addAtom(atomOne);
		group.addAtom(atomTwo);
		// Get the structure
		MmtfSummaryDataBean mmtfSummaryDataBean = MmtfUtils.getStructureInfo(structure);
		assertEquals(mmtfSummaryDataBean.getAllAtoms(), atomList);
		assertEquals(testMap, mmtfSummaryDataBean.getChainIdToIndexMap());
		assertEquals(chainList, mmtfSummaryDataBean.getAllChains());
		assertEquals(1, mmtfSummaryDataBean.getNumBonds());
	}