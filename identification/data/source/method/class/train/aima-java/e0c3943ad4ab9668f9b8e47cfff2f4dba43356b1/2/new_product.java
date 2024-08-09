public void iterateOverTable(Iterator pti,
			AssignmentProposition... fixedValues) {
		Map<RandomVariable, Object> possibleWorld = new LinkedHashMap<RandomVariable, Object>();
		MixedRadixNumber tableMRN = new MixedRadixNumber(0, radices);
		int[] tableRadixValues = new int[radices.length];

		// Assert that the Random Variables for the fixed values
		// are part of this probability table and assign
		// all the fixed values to the possible world.
		for (AssignmentProposition ap : fixedValues) {
			if (!randomVarInfo.containsKey(ap.getTermVariable())) {
				throw new IllegalArgumentException("Assignment proposition ["
						+ ap + "] does not belong to this probability table.");
			}
			possibleWorld.put(ap.getTermVariable(), ap.getValue());
			RVInfo fixedRVI = randomVarInfo.get(ap.getTermVariable());
			tableRadixValues[fixedRVI.getRadixIdx()] = fixedRVI
					.getIdxForDomain(ap.getValue());
		}
		// If have assignments for all the random variables
		// in this probability table
		if (fixedValues.length == randomVarInfo.size()) {
			// Then only 1 iteration call is required.
			pti.iterate(possibleWorld, getValue(fixedValues));
		} else {
			// Else iterate over the non-fixed values
			Set<RandomVariable> freeVariables = SetOps.difference(
					this.randomVarInfo.keySet(), possibleWorld.keySet());
			Map<RandomVariable, RVInfo> freeVarInfo = new LinkedHashMap<RandomVariable, RVInfo>();
			// Remove the fixed Variables
			for (RandomVariable fv : freeVariables) {
				freeVarInfo.put(fv, new RVInfo(fv));
			}
			int[] freeRadixValues = createRadixs(freeVarInfo);
			MixedRadixNumber freeMRN = new MixedRadixNumber(0, freeRadixValues);
			Object fval = null;
			// Iterate through all combinations of the free variables
			do {
				// Put the current assignments for the free variables
				// into the possible world and update
				// the current index in the table MRN
				for (RVInfo freeRVI : freeVarInfo.values()) {
					fval = freeRVI.getDomainValueAt(freeMRN
							.getCurrentNumeralValue(freeRVI.getRadixIdx()));
					possibleWorld.put(freeRVI.getVariable(), fval);

					tableRadixValues[randomVarInfo.get(freeRVI.getVariable())
							.getRadixIdx()] = freeRVI.getIdxForDomain(fval);
				}
				pti.iterate(possibleWorld, values[(int) tableMRN
						.getCurrentValueFor(tableRadixValues)]);

			} while (freeMRN.increment());
		}
	}