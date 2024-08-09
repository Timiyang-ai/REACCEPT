public Hypothesis currentBestLearning(List<LogicalExample> examples,Hypothesis h,List<LogicalExample> examplesSoFar){
		if (examples.isEmpty())
			return h;
		LogicalExample e = examples.get(0);
        examplesSoFar.add(e);
		if (h.isConsistent(e)) {
            return currentBestLearning(examples.subList(1, examples.size()), h,examplesSoFar);
        }
		else if ((h.predict(e))&&(!e.getGoal())){
			for (Hypothesis hypothesis :
					h.specialisations( examplesSoFar)){
			    if (hypothesis.isConsistent(examplesSoFar)) {
                    Hypothesis newHypothesis = currentBestLearning(examples.subList(1, examples.size()), hypothesis,examplesSoFar);
                    if (newHypothesis != null) {
                        return newHypothesis;
                    }
                }
			}
		}
		else if ((!h.predict(e))&&(e.getGoal())){
            for (Hypothesis hypothesis :
                    h.generalisations(examplesSoFar)) {
                if (hypothesis.isConsistent(examplesSoFar)) {
                    Hypothesis newHypothesis = currentBestLearning(examples.subList(1, examples.size()), hypothesis,examplesSoFar);
                    if (newHypothesis != null) {
                        return newHypothesis;
                    }
                }
            }
        }
		return null;
	}