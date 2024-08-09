public double[] transform(final double[] fitParms) {
    ArgChecker.isTrue(fitParms.length == _n - 1, "length of fitParms is {}, but must be {} ", fitParms.length, _n - 1);
    final double[] s2 = new double[_n - 1];
    final double[] c2 = new double[_n - 1];
    for (int j = 0; j < _n - 1; j++) {
      double temp = Math.sin(fitParms[j]);
      temp *= temp;
      s2[j] = temp;
      c2[j] = 1.0 - temp;
    }

    final double[] res = new double[_n];
    for (int i = 0; i < _n; i++) {
      double prod = 1.0;
      for (int j = 0; j < _n - 1; j++) {
        if (_set[i][j] == 1) {
          prod *= s2[j];
        } else if (_set[i][j] == -1) {
          prod *= c2[j];
        }
      }
      res[i] = prod;
    }
    return res;
  }