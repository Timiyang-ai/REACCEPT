public boolean changeState(StateVertex nextState) {
		if (nextState == null) {
			LOGGER.info("nextState given is null");
			return false;
		}
		LOGGER.debug("Trying to change to state: '{}' from: '{}'", nextState.getName(),
				currentState.getName());

		if (stateFlowGraph.canGoTo(currentState, nextState)) {

			LOGGER.debug("Changed to state: '{}' from: '{}'", nextState.getName(),
					currentState.getName());

			setCurrentState(nextState);

			return true;
		} else {
			LOGGER.info("Cannot go to state: '{}' from: '{}'", nextState.getName(),
					currentState.getName());
			return false;
		}
	}