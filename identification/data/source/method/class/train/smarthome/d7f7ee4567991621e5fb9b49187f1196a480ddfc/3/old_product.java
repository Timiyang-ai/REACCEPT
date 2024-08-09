@Override
    public void setState(State state) {
        if (state instanceof Convertible) {
            state = ((Convertible) state).as(PercentType.class);
        }
        super.setState(state);
    }