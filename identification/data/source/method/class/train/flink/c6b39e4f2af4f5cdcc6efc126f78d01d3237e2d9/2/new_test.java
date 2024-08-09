	private <T extends StateObject> boolean checkResultAsExpected(
		Function<OperatorSubtaskState, StateObjectCollection<T>> extractor,
		Function<PrioritizedOperatorSubtaskState, List<StateObjectCollection<T>>> extractor2,
		PrioritizedOperatorSubtaskState prioritizedResult,
		OperatorSubtaskState... expectedOrdered) {

		List<StateObjectCollection<T>> collector = new ArrayList<>(expectedOrdered.length);
		for (OperatorSubtaskState operatorSubtaskState : expectedOrdered) {
			collector.add(extractor.apply(operatorSubtaskState));
		}

		return checkRepresentSameOrder(
			extractor2.apply(prioritizedResult).iterator(),
			collector.toArray(new StateObjectCollection[collector.size()]));
	}