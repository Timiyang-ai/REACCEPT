public Hypothesis currentBestLearning(List<FOLExample> examples) {
		
		// TODO-use the default from pg 679 for now.
		String c1 = "patrons(v,Some)";
		String c2 = "patrons(v,Full) AND (hungry(v) AND type(v,French))";
		String c3 = "patrons(v,Full) AND (hungry(v) AND (type(v,Thai) AND fri_sat(v)))";
		String c4 = "patrons(v,Full) AND (hungry(v) AND type(v,Burger))";
		String sh = "FORALL v (will_wait(v) <=> ("+c1+" OR ("+c2+" OR ("+c3+" OR ("+c4+")))))";
		
		Hypothesis H = new Hypothesis(kbForLearning.tell(sh));

		// TODO
		// * H <- any hypothesis consistent with the first example in examples.
		// * for each remaining example in examples do
		// * if e is false positive for H then
		// * H <- choose a specialization of H consistent with examples
		// * else if e is false negative for H then
		// * H <- choose a generalization of H consistent with examples
		// * if no consistent specialization/generalization can be found then
		// fail
		// * return H
		return H;
	}