protected <T extends StateObject> List<StateObjectCollection<T>> resolvePrioritizedAlternatives(
			StateObjectCollection<T> jobManagerState,
			List<StateObjectCollection<T>> alternativesByPriority,
			BiFunction<T, T, Boolean> approveFun) {

			// Nothing to resolve if there are no alternatives, or the ground truth has already no state, or if we can
			// assume that a rescaling happened because we find more than one handle in the JM state (this is more a sanity
			// check).
			if (alternativesByPriority == null
				|| alternativesByPriority.isEmpty()
				|| !jobManagerState.hasState()
				|| jobManagerState.size() != 1) {

				return Collections.singletonList(jobManagerState);
			}

			// As we know size is == 1
			T reference = jobManagerState.iterator().next();

			// This will contain the end result, we initialize it with the potential max. size.
			List<StateObjectCollection<T>> approved =
				new ArrayList<>(1 + alternativesByPriority.size());

			for (StateObjectCollection<T> alternative : alternativesByPriority) {

				// We found an alternative to the JM state if it has state, we have a 1:1 relationship, and the
				// approve-function signaled true.
				if (alternative != null
					&& alternative.hasState()
					&& alternative.size() == 1
					&& BooleanUtils.isTrue(approveFun.apply(reference, alternative.iterator().next()))) {

					approved.add(alternative);
				}
			}

			// Of course we include the ground truth as last alternative.
			approved.add(jobManagerState);
			return Collections.unmodifiableList(approved);
		}