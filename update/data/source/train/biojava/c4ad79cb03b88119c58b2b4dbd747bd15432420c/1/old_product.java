public Structure getStructureForDomain(ScopDomain domain, ScopDatabase scopDatabase, boolean strictLigandHandling)
			throws IOException, StructureException {

		String pdbId = domain.getPdbId();
		Structure fullStructure = getStructureForPdbId(pdbId);
		Structure structure = domain.reduce(fullStructure);

		// TODO It would be better to move all of this into the reduce method,
		// but that would require ligand handling properties in StructureIdentifiers

		// because ligands sometimes occur after TER records in PDB files, we may need to add some ligands back in
		// specifically, we add a ligand if and only if it occurs within the domain
		AtomPositionMap map = null;
		List<ResidueRangeAndLength> rrs = null;
		if (strictLigandHandling) {
			map = new AtomPositionMap(StructureTools.getAllAtomArray(fullStructure), AtomPositionMap.ANYTHING_MATCHER);
			rrs = ResidueRangeAndLength.parseMultiple(domain.getRanges(), map);
		}
		for (Chain chain : fullStructure.getChains()) {
			if (!structure.hasChain(chain.getChainID())) {
				continue; // we can't do anything with a chain our domain
			}
			// doesn't contain
			Chain newChain = structure.getChainByPDB(chain.getChainID());
			List<Group> ligands = StructureTools.filterLigands(chain.getAtomGroups());
			for (Group group : ligands) {
				boolean shouldContain = true;
				if (strictLigandHandling) {
					shouldContain = false; // whether the ligand occurs within the domain
					for (ResidueRange rr : rrs) {
						if (rr.contains(group.getResidueNumber(), map)) {
							shouldContain = true;
						}
					}
				}
				boolean alreadyContains = newChain.getAtomGroups().contains(group); // we don't want to add duplicate
																					// ligands
				if (shouldContain && !alreadyContains) {
					newChain.addGroup(group);
				}
			}
		}

		// build a more meaningful description for the new structure
		StringBuilder header = new StringBuilder();
		header.append(domain.getClassificationId());
		if (scopDatabase != null) {
			int sf = domain.getSuperfamilyId();
			ScopDescription description = scopDatabase.getScopDescriptionBySunid(sf);
			if (description != null) {
				header.append(" | ");
				header.append(description.getDescription());
			}
		}
		structure.getPDBHeader().setDescription(header.toString());

		return structure;

	}