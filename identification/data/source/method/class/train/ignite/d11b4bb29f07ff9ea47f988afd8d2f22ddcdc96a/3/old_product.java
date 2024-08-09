@Override public double compute(Vector a, Vector b)
        throws CardinalityException {
        IgniteDoubleFunction<Double> fun = (value -> {
            if (value == 0) return 0.0;
            else return 1.0;
        });
        return MatrixUtil.localCopyOf(a).minus(b).foldMap(Functions.PLUS, fun, 0d);
    }