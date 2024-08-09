public boolean divideInternally(SubunitClustererParameters clusterParams)
			throws StructureException {

		CESymmParameters cesym_params = new CESymmParameters();
		cesym_params.setMinCoreLength(clusterParams.getMinimumSequenceLength());
		cesym_params.setGaps(false); // We want no gaps between the repeats

		// Analyze the internal symmetry of the representative subunit
		CeSymmResult result = CeSymm.analyze(subunits.get(representative)
				.getRepresentativeAtoms(), cesym_params);

		if (!result.isSignificant())
			return false;

		double rmsd = result.getMultipleAlignment().getScore(
				MultipleAlignmentScorer.RMSD);
		if (rmsd > clusterParams.getRMSDThreshold())
			return false;

		double coverage = result.getMultipleAlignment().getCoverages().get(0)
				* result.getNumRepeats();
		if (coverage < clusterParams.getStructureCoverageThreshold())
			return false;

		logger.info("SubunitCluster is internally symmetric with {} repeats, "
				+ "{} RMSD and {} coverage", result.getNumRepeats(), rmsd,
				coverage);

		// Divide if symmety was significant with RMSD and coverage sufficient
		List<List<Integer>> alignedRes = result.getMultipleAlignment()
				.getBlock(0).getAlignRes();

		List<List<Integer>> columns = new ArrayList<List<Integer>>();
		for (int s = 0; s < alignedRes.size(); s++)
			columns.add(new ArrayList<Integer>(alignedRes.get(s).size()));

		// Extract the aligned columns of each repeat in the Subunit
		for (int col = 0; col < alignedRes.get(0).size(); col++) {

			// Check that all aligned residues are part of the Cluster
			boolean missing = false;
			for (int s = 0; s < alignedRes.size(); s++) {
				if (!subunitEQR.get(representative).contains(
						alignedRes.get(s).get(col))) {
					missing = true;
					break;
				}
			}

			// Skip the column if any residue was not part of the cluster
			if (missing)
				continue;

			for (int s = 0; s < alignedRes.size(); s++) {
				columns.get(s).add(
						subunitEQR.get(representative).indexOf(
								alignedRes.get(s).get(col)));
			}
		}

		// Divide the Subunits in their repeats
		List<Subunit> newSubunits = new ArrayList<Subunit>(subunits.size()
				* columns.size());
		List<List<Integer>> newSubunitEQR = new ArrayList<List<Integer>>(
				subunits.size() * columns.size());

		for (int s = 0; s < subunits.size(); s++) {
			for (int r = 0; r < columns.size(); r++) {

				// Calculate start and end residues of the new Subunit
				int start = subunitEQR.get(s).get(columns.get(r).get(0));
				int end = subunitEQR.get(s).get(
						columns.get(r).get(columns.get(r).size() - 1));

				Atom[] reprAtoms = Arrays.copyOfRange(subunits.get(s)
						.getRepresentativeAtoms(), start, end + 1);

				newSubunits.add(new Subunit(reprAtoms, subunits.get(s)
						.getName(), subunits.get(s).getIdentifier(), subunits
						.get(s).getStructure()));

				// Recalculate equivalent residues
				List<Integer> eqr = new ArrayList<Integer>();
				for (int p = 0; p < columns.get(r).size(); p++) {
					eqr.add(subunitEQR.get(s).get(columns.get(r).get(p))
							- start);
				}
				newSubunitEQR.add(eqr);
			}
		}

		subunits = newSubunits;
		subunitEQR = newSubunitEQR;

		// Update representative
		for (int s = 0; s < subunits.size(); s++) {
			if (subunits.get(s).size() > subunits.get(representative).size())
				representative = s;
		}

		method = SubunitClustererMethod.STRUCTURE;
		pseudoStoichiometric = true;
		return true;
	}