public boolean mergeSequence(SubunitCluster other, SubunitClustererParameters params,
								 PairwiseSequenceAlignerType alignerType,
								 GapPenalty gapPenalty,
								 SubstitutionMatrix<AminoAcidCompound> subsMatrix)
			throws CompoundNotFoundException {

		// Extract the protein sequences as BioJava alignment objects
		ProteinSequence thisSequence = this.subunits.get(this.representative)
				.getProteinSequence();
		ProteinSequence otherSequence = other.subunits
				.get(other.representative).getProteinSequence();

		// Perform the alignment with provided parameters
		PairwiseSequenceAligner<ProteinSequence, AminoAcidCompound> aligner = Alignments
				.getPairwiseAligner(thisSequence, otherSequence, alignerType,
						gapPenalty, subsMatrix);

		double sequenceIdentity;
		if(params.isUseGlobalMetrics()) {
			sequenceIdentity = aligner.getPair().getPercentageOfIdentity(true);
		} else {
			sequenceIdentity = aligner.getPair().getPercentageOfIdentity(false);
		}

		if (sequenceIdentity < params.getSequenceIdentityThreshold())
			return false;

		double sequenceCoverage = 0;
		if(params.isUseSequenceCoverage()) {
			// Calculate real coverage (subtract gaps in both sequences)
			double gaps1 = aligner.getPair().getAlignedSequence(1)
					.getNumGapPositions();
			double gaps2 = aligner.getPair().getAlignedSequence(2)
					.getNumGapPositions();
			double lengthAlignment = aligner.getPair().getLength();
			double lengthThis = aligner.getQuery().getLength();
			double lengthOther = aligner.getTarget().getLength();
			sequenceCoverage = (lengthAlignment - gaps1 - gaps2)
					/ Math.max(lengthThis, lengthOther);

			if (sequenceCoverage < params.getSequenceCoverageThreshold())
				return false;
		}

		logger.info(String.format("SubunitClusters are similar in sequence "
						+ "with %.2f sequence identity and %.2f coverage", sequenceIdentity,
				sequenceCoverage));

		// If coverage and sequence identity sufficient, merge other and this
		List<Integer> thisAligned = new ArrayList<Integer>();
		List<Integer> otherAligned = new ArrayList<Integer>();

		// Extract the aligned residues of both Subunit
		for (int p = 1; p < aligner.getPair().getLength() + 1; p++) {

			// Skip gaps in any of the two sequences
			if (aligner.getPair().getAlignedSequence(1).isGap(p))
				continue;
			if (aligner.getPair().getAlignedSequence(2).isGap(p))
				continue;

			int thisIndex = aligner.getPair().getIndexInQueryAt(p) - 1;
			int otherIndex = aligner.getPair().getIndexInTargetAt(p) - 1;

			// Only consider residues that are part of the SubunitCluster
			if (this.subunitEQR.get(this.representative).contains(thisIndex)
					&& other.subunitEQR.get(other.representative).contains(
							otherIndex)) {
				thisAligned.add(thisIndex);
				otherAligned.add(otherIndex);
			}
		}

		// Do a List intersection to find out which EQR columns to remove
		List<Integer> thisRemove = new ArrayList<Integer>();
		List<Integer> otherRemove = new ArrayList<Integer>();

		for (int t = 0; t < this.subunitEQR.get(this.representative).size(); t++) {
			// If the index is aligned do nothing, otherwise mark as removing
			if (!thisAligned.contains(this.subunitEQR.get(this.representative)
					.get(t)))
				thisRemove.add(t);
		}

		for (int t = 0; t < other.subunitEQR.get(other.representative).size(); t++) {
			// If the index is aligned do nothing, otherwise mark as removing
			if (!otherAligned.contains(other.subunitEQR.get(
					other.representative).get(t)))
				otherRemove.add(t);
		}
		// Now remove unaligned columns, from end to start
		Collections.sort(thisRemove);
		Collections.reverse(thisRemove);
		Collections.sort(otherRemove);
		Collections.reverse(otherRemove);

		for (int t = 0; t < thisRemove.size(); t++) {
			for (List<Integer> eqr : this.subunitEQR) {
				int column = thisRemove.get(t);
				eqr.remove(column);
			}
		}

		for (int t = 0; t < otherRemove.size(); t++) {
			for (List<Integer> eqr : other.subunitEQR) {
				int column = otherRemove.get(t);
				eqr.remove(column);
			}
		}

		// The representative is the longest sequence
		if (this.subunits.get(this.representative).size() < other.subunits.get(
				other.representative).size())
			this.representative = other.representative + subunits.size();

		this.subunits.addAll(other.subunits);
		this.subunitEQR.addAll(other.subunitEQR);

		this.method = SubunitClustererMethod.SEQUENCE;

		pseudoStoichiometric = !params.isHighConfidenceScores(sequenceIdentity,sequenceCoverage);

		return true;
	}