public double[] inverseTransform(final double[] modelParms) {
    ArgChecker.isTrue(modelParms.length == _n, "length of modelParms is {}, but must be {} ", modelParms.length, _n);

    final double[] res = new double[_n - 1];
    final double[] cum = new double[_n + 1];

    double sum = 0.0;
    for (int i = 0; i < _n; i++) {
      sum += modelParms[i];
      cum[i + 1] = sum;
    }
    ArgChecker.isTrue(Math.abs(sum - 1.0) < TOL, "sum of elements is {}. Must be 1.0", sum);

    cal(cum, 1.0, 0, _n, 0, res);

    for (int i = 0; i < _n - 1; i++) {
      res[i] = Math.asin(Math.sqrt(res[i]));
    }
    return res;
  }