@Override
    public void setState(State state) {
        // try conversion
        State convertedState = state.as(PercentType.class);
        if (convertedState != null) {
            applyState(convertedState);
        } else {
            applyState(state);
        }
    }