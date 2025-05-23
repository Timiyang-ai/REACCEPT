public Hypothesis currentBestLearning(List<LogicalExample> examples, Hypothesis h, List<LogicalExample> examplesSoFar) {
        // if examples is empty then
        if (examples.isEmpty()) {
            //return h
            return h;
        }
        // e <- FIRST(examples)
        LogicalExample e = examples.get(0);
        examplesSoFar.add(e);
        // if e is consistent with h then
        if (h.isConsistent(e)) {
            // return CURRENT-BEST-LEARNING(REST(examples), h)
            return currentBestLearning(examples.subList(1, examples.size()), h, examplesSoFar);
        }
        // else if e is a false positive for h then
        else if ((h.predict(e)) && (!e.getGoal())) {
            // for each h' in specializations of h consistent with examples seen so far do
            for (Hypothesis hypothesis :
                    h.specialisations(examplesSoFar)) {
                if (hypothesis.isConsistent(examplesSoFar)) {
                    // h'' <- CURRENT-BEST-LEARNING(REST(examples), h')
                    Hypothesis newHypothesis = currentBestLearning(examples.subList(1, examples.size())
                            , hypothesis, examplesSoFar);
                    // if h'' != fail then return h''
                    if (newHypothesis != null) {
                        return newHypothesis;
                    }
                }
            }
        }
        // else if e is a false negative for h then
        else if ((!h.predict(e)) && (e.getGoal())) {
            // for each h' in generalization of h consistent with examples seen so far do
            for (Hypothesis hypothesis :
                    h.generalisations(examplesSoFar)) {
                if (hypothesis.isConsistent(examplesSoFar)) {
                    // h'' <- CURRENT-BEST-LEARNING(REST(examples), h')
                    Hypothesis newHypothesis = currentBestLearning(examples.subList(1, examples.size()),
                            hypothesis, examplesSoFar);
                    // if h'' != fail then return h''
                    if (newHypothesis != null) {
                        return newHypothesis;
                    }
                }
            }
        }
        // return fail
        return null;
    }