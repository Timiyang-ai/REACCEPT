@Test
	public void testGetStructureForDomain3() throws IOException, StructureException {
		String ranges = "E:";
		Structure whole = cache.getStructure("1I3O");
		AtomPositionMap map = new AtomPositionMap(StructureTools.getAllAtomArray(whole), AtomPositionMap.ANYTHING_MATCHER);
		List<ResidueRangeAndLength> rrs = ResidueRangeAndLength.parseMultiple(ranges, map);
		int expectedLengthE = rrs.get(0).getLength();
		Structure structure = cache.getStructureForDomain("d1i3oe_");
		assertEquals(1, structure.getPolyChains().size());
		Chain e = structure.getChainByPDB("E");
		assertEquals(expectedLengthE, e.getAtomGroups().size());

		Chain eligands = structure.getNonPolyChainByPDB("E");
		List<Group> ligandsE = StructureTools.filterLigands(eligands.getAtomGroups());
		assertEquals(1, ligandsE.size());
	}