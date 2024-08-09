@Override
    public void setState(State state) {
        applyState(state.as(PercentType.class));
    }