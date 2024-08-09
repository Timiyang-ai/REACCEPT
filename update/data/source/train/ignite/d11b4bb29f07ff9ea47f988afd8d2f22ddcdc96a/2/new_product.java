@Override public double compute(Vector a, Vector b)
        throws CardinalityException {
        IgniteDoubleFunction<Double> fun = (value -> value == 0 ? 0.0 : 1.0);

        return MatrixUtil.localCopyOf(a).minus(b).foldMap(Functions.PLUS, fun, 0d);
    }