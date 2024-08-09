public Value dimension(int index) {
        assert index >= 0 && index < dimensionCount;
        return (Value) inputs().get(super.inputCount() + index);
    }