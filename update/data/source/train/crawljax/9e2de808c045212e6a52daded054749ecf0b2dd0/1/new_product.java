public boolean changeState(StateVertex nextState) {
		if (nextState == null) {
			LOGGER.info("nextState given is null");
			return false;
		}
		LOGGER.debug("AFTER: sm.current: " + currentState.getName() + " hold.current: "
		        + nextState.getName());

		if (stateFlowGraph.canGoTo(currentState, nextState)) {

			LOGGER.debug("Changed To state: " + nextState.getName() + " From: "
			        + currentState.getName());

			this.previousState = this.currentState;
			currentState = nextState;

			LOGGER.info("StateMachine's Pointer changed to: " + currentState);

			return true;
		} else {
			LOGGER.info("Cannot change To state: " + nextState.getName() + " From: "
			        + currentState.getName());
			return false;
		}
	}