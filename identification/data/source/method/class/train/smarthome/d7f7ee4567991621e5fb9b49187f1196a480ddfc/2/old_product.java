@Override
    public void setState(State state) {
        // we map UP/DOWN values to the percent values 0 and 100
        if (state == UpDownType.UP) {
            super.setState(PercentType.ZERO);
        } else if (state == UpDownType.DOWN) {
            super.setState(PercentType.HUNDRED);
        } else if (state.getClass() == DecimalType.class) {
            super.setState(new PercentType(((DecimalType) state).toBigDecimal().multiply(new BigDecimal(100))));
        } else {
            super.setState(state);
        }
    }