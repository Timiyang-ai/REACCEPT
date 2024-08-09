@Override
    final public double getDerivative(double input) {
        //output here is a*tanh^2(s*x)
        double E_x = Math.exp(2 * this.slope * input);
        double tanhsx = (E_x - 1d) / (E_x + 1d);
        derivativeOutput = amplitude * slope * (1 - tanhsx * tanhsx);
        return derivativeOutput;
    }