public double[][] jacobian(double[] fitParms) {
    ArgChecker.isTrue(fitParms.length == _n - 1, "length of fitParms is {}, but must be {} ", fitParms.length, _n - 1);
    double[] sin = new double[_n - 1];
    double[] cos = new double[_n - 1];
    for (int j = 0; j < _n - 1; j++) {
      sin[j] = Math.sin(fitParms[j]);
      cos[j] = Math.cos(fitParms[j]);
    }

    double[] a = new double[_n];
    for (int i = 0; i < _n; i++) {
      double prod = 1.0;
      for (int j = 0; j < _n - 1; j++) {
        if (_set[i][j] == 1) {
          prod *= sin[j];
        } else if (_set[i][j] == -1) {
          prod *= cos[j];
        }
      }
      a[i] = 2 * prod * prod;
    }

    double[][] res = new double[_n][_n - 1];
    for (int i = 0; i < _n; i++) {
      for (int j = 0; j < _n - 1; j++) {
        if (_set[i][j] == 1 && a[i] != 0.0) {
          res[i][j] = a[i] * cos[j] / sin[j];
        } else if (_set[i][j] == -1 && a[i] != 0.0) {
          res[i][j] = -a[i] * sin[j] / cos[j];
        }
      }
    }
    return res;
  }