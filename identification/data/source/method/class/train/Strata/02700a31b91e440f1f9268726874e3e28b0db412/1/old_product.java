@Override
  public double volatilityAdjoint2(
      double forward,
      double strike,
      double timeToExpiry,
      SabrFormulaData data,
      double[] volatilityD,
      double[][] volatilityD2) {
    double k = Math.max(strike, 0.000001);
    double alpha = data.getAlpha();
    double beta = data.getBeta();
    double rho = data.getRho();
    double nu = data.getNu();
    // Forward
    double h0 = (1 - beta) / 2;
    double h1 = forward * k;
    double h1h0 = Math.pow(h1, h0);
    double h12 = h1h0 * h1h0;
    double h2 = Math.log(forward / k);
    double h22 = h2 * h2;
    double h23 = h22 * h2;
    double h24 = h23 * h2;
    double f1 = h1h0 * (1 + h0 * h0 / 6.0 * (h22 + h0 * h0 / 20.0 * h24));
    double f2 = nu / alpha * h1h0 * h2;
    double f3 = h0 * h0 / 6.0 * alpha * alpha / h12 + rho * beta * nu * alpha / 4.0 / h1h0 + (2 - 3 * rho * rho) /
        24.0 * nu * nu;
    double sqrtf2 = Math.sqrt(1 - 2 * rho * f2 + f2 * f2);
    double f2x = 0.0;
    double x = 0.0, xp = 0, xpp = 0;
    if (DoubleMath.fuzzyEquals(f2, 0.0, SMALL_Z)) {
      f2x = 1.0 - 0.5 * f2 * rho; //small f2 expansion to f2^2 terms
    } else {
      if (DoubleMath.fuzzyEquals(rho, 1.0, RHO_EPS)) {
        x = f2 < 1.0 ?
            -Math.log(1.0 - f2) - 0.5 * Math.pow(f2 / (f2 - 1.0), 2) * (1.0 - rho) :
            Math.log(2.0 * f2 - 2.0) - Math.log(1.0 - rho);
      } else {
        x = Math.log((sqrtf2 + f2 - rho) / (1 - rho));
      }
      xp = 1. / sqrtf2;
      xpp = (rho - f2) / Math.pow(sqrtf2, 3.0);
      f2x = f2 / x;
    }
    double sigma = Math.max(MIN_VOL, alpha / f1 * f2x * (1 + f3 * timeToExpiry));
    // First level
    double h0Dbeta = -0.5;
    double sigmaDf1 = -sigma / f1;
    double sigmaDf2 = 0;
    if (DoubleMath.fuzzyEquals(f2, 0.0, SMALL_Z)) {
      sigmaDf2 = alpha / f1 * (1 + f3 * timeToExpiry) * -0.5 * rho;
    } else {
      sigmaDf2 = alpha / f1 * (1 + f3 * timeToExpiry) * (1.0 / x - f2 * xp / (x * x));
    }
    double sigmaDf3 = alpha / f1 * f2x * timeToExpiry;
    double sigmaDf4 = f2x / f1 * (1 + f3 * timeToExpiry);
    double sigmaDx = -alpha / f1 * f2 / (x * x) * (1 + f3 * timeToExpiry);
    double[][] sigmaD2ff = new double[3][3];
    sigmaD2ff[0][0] = -sigmaDf1 / f1 + sigma / (f1 * f1); //OK
    sigmaD2ff[0][1] = -sigmaDf2 / f1;
    sigmaD2ff[0][2] = -sigmaDf3 / f1;
    if (DoubleMath.fuzzyEquals(f2, 0.0, SMALL_Z)) {
      sigmaD2ff[1][2] = alpha / f1 * -0.5 * rho * timeToExpiry;
    } else {
      sigmaD2ff[1][1] = alpha / f1 * (1 + f3 * timeToExpiry) *
          (-2 * xp / (x * x) - f2 * xpp / (x * x) + 2 * f2 * xp * xp / (x * x * x));
      sigmaD2ff[1][2] = alpha / f1 * timeToExpiry * (1.0 / x - f2 * xp / (x * x));
    }
    sigmaD2ff[2][2] = 0.0;
    //      double sigma = alpha / f1 * f2x * (1 + f3 * theta);
    // Second level
    double[] f1Dh = new double[3];
    double[] f2Dh = new double[3];
    double[] f3Dh = new double[3];
    f1Dh[0] = h1h0 * (h0 * (h22 / 3.0 + h0 * h0 / 40.0 * h24)) + Math.log(h1) * f1;
    f1Dh[1] = h0 * f1 / h1;
    f1Dh[2] = h1h0 * (h0 * h0 / 6.0 * (2.0 * h2 + h0 * h0 / 5.0 * h23));
    f2Dh[0] = Math.log(h1) * f2;
    f2Dh[1] = h0 * f2 / h1;
    f2Dh[2] = nu / alpha * h1h0;
    f3Dh[0] = h0 / 3.0 * alpha * alpha / h12 - 2 * h0 * h0 / 6.0 * alpha * alpha / h12 * Math.log(h1) - rho * beta *
        nu * alpha / 4.0 / h1h0 * Math.log(h1);
    f3Dh[1] = -2 * h0 * h0 / 6.0 * alpha * alpha / h12 * h0 / h1 - rho * beta * nu * alpha / 4.0 / h1h0 * h0 / h1;
    f3Dh[2] = 0.0;
    double[] f1Dp = new double[4]; // Derivative to sabr parameters
    double[] f2Dp = new double[4];
    double[] f3Dp = new double[4];
    double[] f4Dp = new double[4];
    f1Dp[0] = 0.0;
    f1Dp[1] = f1Dh[0] * h0Dbeta;
    f1Dp[2] = 0.0;
    f1Dp[3] = 0.0;
    f2Dp[0] = -f2 / alpha;
    f2Dp[1] = f2Dh[0] * h0Dbeta;
    f2Dp[2] = 0.0;
    f2Dp[3] = h1h0 * h2 / alpha;
    f3Dp[0] = h0 * h0 / 3.0 * alpha / h12 + rho * beta * nu / 4.0 / h1h0;
    f3Dp[1] = rho * nu * alpha / 4.0 / h1h0 + f3Dh[0] * h0Dbeta;
    f3Dp[2] = beta * nu * alpha / 4.0 / h1h0 - rho / 4.0 * nu * nu;
    f3Dp[3] = rho * beta * alpha / 4.0 / h1h0 + (2 - 3 * rho * rho) / 12.0 * nu;
    f4Dp[0] = 1.0;
    f4Dp[1] = 0.0;
    f4Dp[2] = 0.0;
    f4Dp[3] = 0.0;
    double sigmaDh1 = sigmaDf1 * f1Dh[1] + sigmaDf2 * f2Dh[1] + sigmaDf3 * f3Dh[1];
    double sigmaDh2 = sigmaDf1 * f1Dh[2] + sigmaDf2 * f2Dh[2] + sigmaDf3 * f3Dh[2];
    double[][] f1D2hh = new double[2][2]; // No h0
    double[][] f2D2hh = new double[2][2];
    double[][] f3D2hh = new double[2][2];
    f1D2hh[0][0] = h0 * (h0 - 1) * f1 / (h1 * h1);
    f1D2hh[0][1] = h0 * h1h0 / h1 * h0 * h0 / 6.0 * (2.0 * h2 + 4.0 * h0 * h0 / 20.0 * h23);
    f1D2hh[1][1] = h1h0 * (h0 * h0 / 6.0 * (2.0 + 12.0 * h0 * h0 / 20.0 * h2));
    f2D2hh[0][0] = h0 * (h0 - 1) * f2 / (h1 * h1);
    f2D2hh[0][1] = nu / alpha * h0 * h1h0 / h1;
    f2D2hh[1][1] = 0.0;
    f3D2hh[0][0] = 2 * h0 * (2 * h0 + 1) * h0 * h0 / 6.0 * alpha * alpha / (h12 * h1 * h1) + h0 * (h0 + 1) * rho *
        beta * nu * alpha / 4.0 / (h1h0 * h1 * h1);
    f3D2hh[0][1] = 0.0;
    f3D2hh[1][1] = 0.0;
    double[][] sigmaD2hh = new double[2][2]; // No h0
    for (int loopx = 0; loopx < 2; loopx++) {
      for (int loopy = loopx; loopy < 2; loopy++) {
        sigmaD2hh[loopx][loopy] = (sigmaD2ff[0][0] * f1Dh[loopy + 1] + sigmaD2ff[0][1] * f2Dh[loopy + 1] + sigmaD2ff[0][2] *
            f3Dh[loopy + 1]) *
            f1Dh[loopx + 1] +
            sigmaDf1 *
                f1D2hh[loopx][loopy] +
            (sigmaD2ff[0][1] * f1Dh[loopy + 1] + sigmaD2ff[1][1] * f2Dh[loopy + 1] + sigmaD2ff[1][2] * f3Dh[loopy + 1]) *
                f2Dh[loopx + 1] +
            sigmaDf2 *
                f2D2hh[loopx][loopy] +
            (sigmaD2ff[0][2] * f1Dh[loopy + 1] + sigmaD2ff[1][2] * f2Dh[loopy + 1] + sigmaD2ff[2][2] * f3Dh[loopy + 1]) *
                f3Dh[loopx + 1] +
            sigmaDf3 * f3D2hh[loopx][loopy];
      }
    }
    // Third level
    double h1Df = k;
    double h1Dk = forward;
    double h1D2ff = 0.0;
    double h1D2kf = 1.0;
    double h1D2kk = 0.0;
    double h2Df = 1.0 / forward;
    double h2Dk = -1.0 / k;
    double h2D2ff = -1 / (forward * forward);
    double h2D2fk = 0.0;
    double h2D2kk = 1.0 / (k * k);
    volatilityD[0] = sigmaDh1 * h1Df + sigmaDh2 * h2Df;
    volatilityD[1] = sigmaDh1 * h1Dk + sigmaDh2 * h2Dk;
    volatilityD[2] = sigmaDf1 * f1Dp[0] + sigmaDf2 * f2Dp[0] + sigmaDf3 * f3Dp[0] + sigmaDf4 * f4Dp[0];
    volatilityD[3] = sigmaDf1 * f1Dp[1] + sigmaDf2 * f2Dp[1] + sigmaDf3 * f3Dp[1] + sigmaDf4 * f4Dp[1];
    if (DoubleMath.fuzzyEquals(f2, 0.0, SMALL_Z)) {
      volatilityD[4] = -0.5 * f2 + sigmaDf3 * f3Dp[2];
    } else {
      double xDr;
      if (DoubleMath.fuzzyEquals(rho, 1.0, RHO_EPS)) {
        xDr = f2 > 1.0 ?
            1.0 / (1.0 - rho) + (0.5 - f2) / (f2 - 1.0) / (f2 - 1.0) :
            0.5 *
                Math.pow(f2 / (1.0 - f2), 2.0) +
                0.25 * (f2 - 4.0) * Math.pow(f2 / (f2 - 1.0), 3) / (f2 - 1.0) *
                    (1.0 - rho);
        if (Doubles.isFinite(xDr)) {
          volatilityD[4] = sigmaDf1 * f1Dp[2] + sigmaDx * xDr + sigmaDf3 * f3Dp[2] + sigmaDf4 * f4Dp[2];
        } else {
          volatilityD[4] = Double.NEGATIVE_INFINITY;
        }
      } else {
        xDr = (-f2 / sqrtf2 - 1 + (sqrtf2 + f2 - rho) / (1 - rho)) / (sqrtf2 + f2 - rho);
        volatilityD[4] = sigmaDf1 * f1Dp[2] + sigmaDx * xDr + sigmaDf3 * f3Dp[2] + sigmaDf4 * f4Dp[2];
      }
    }
    volatilityD[5] = sigmaDf1 * f1Dp[3] + sigmaDf2 * f2Dp[3] + sigmaDf3 * f3Dp[3] + sigmaDf4 * f4Dp[3];
    volatilityD2[0][0] = (sigmaD2hh[0][0] * h1Df + sigmaD2hh[0][1] * h2Df) * h1Df + sigmaDh1 * h1D2ff +
        (sigmaD2hh[0][1] * h1Df + sigmaD2hh[1][1] * h2Df) * h2Df + sigmaDh2 * h2D2ff;
    volatilityD2[0][1] = (sigmaD2hh[0][0] * h1Dk + sigmaD2hh[0][1] * h2Dk) * h1Df + sigmaDh1 * h1D2kf +
        (sigmaD2hh[0][1] * h1Dk + sigmaD2hh[1][1] * h2Dk) * h2Df + sigmaDh2 * h2D2fk;
    volatilityD2[1][0] = volatilityD2[0][1];
    volatilityD2[1][1] = (sigmaD2hh[0][0] * h1Dk + sigmaD2hh[0][1] * h2Dk) * h1Dk + sigmaDh1 * h1D2kk +
        (sigmaD2hh[0][1] * h1Dk + sigmaD2hh[1][1] * h2Dk) * h2Dk + sigmaDh2 * h2D2kk;
    return sigma;
  }