@Override
    final public double getDerivative(double net) {
        return (1d - output * output);
    }