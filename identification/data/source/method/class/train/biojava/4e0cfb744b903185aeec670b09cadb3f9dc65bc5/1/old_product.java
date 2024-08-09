public static MmtfSummaryDataBean getStructureInfo(Structure structure) {
		MmtfSummaryDataBean mmtfSummaryDataBean = new MmtfSummaryDataBean();
		// Get all the atoms
		List<Atom> theseAtoms = new ArrayList<>();
		List<Chain> allChains = new ArrayList<>();
		Map<String, Integer> chainIdToIndexMap = new HashMap<>();
		int chainCounter = 0;
		int bondCount = 0;
		mmtfSummaryDataBean.setAllAtoms(theseAtoms);
		mmtfSummaryDataBean.setAllChains(allChains);
		mmtfSummaryDataBean.setChainIdToIndexMap(chainIdToIndexMap);
		for (int i=0; i<structure.nrModels(); i++){
			List<Chain> chains = structure.getModel(i);
			allChains.addAll(chains);
			for (Chain chain : chains) {
				String idOne = chain.getChainID();
				if (!chainIdToIndexMap.containsKey(idOne)) { 
					chainIdToIndexMap.put(idOne, chainCounter);
				}
				chainCounter++;
				for (Group g : chain.getAtomGroups()) {
					for(Atom atom: getAtomsForGroup(g)){
						theseAtoms.add(atom);		
						// If both atoms are in the group
						if (atom.getBonds()!=null){
							bondCount+=atom.getBonds().size();
						}
					}
				}
			}
		}
		// Assumes all bonds are referenced twice
		mmtfSummaryDataBean.setNumBonds(bondCount/2);
		return mmtfSummaryDataBean;

	}