@Test
    public void testLearn() {
        System.out.println("learn");

        int k = 3;
        double[] mu = Math.colMeans(USArrests);
        double[][] cov = Math.cov(USArrests);
        for (int i = 0; i < USArrests.length; i++) {
           Math.minus(USArrests[i], mu);
        }

        double r = 0.00001;
        GHA gha = new GHA(4, k, r);
        for (int iter = 1, t = 0; iter <= 1000; iter++) {
            double error = 0.0;
            for (int i = 0; i < USArrests.length; i++, t++) {
                error += gha.learn(USArrests[i]);
            }
            error /= USArrests.length;

            if (iter % 100 == 0) {
                System.out.format("Iter %3d, Error = %.5g%n", iter, error);
            }
        }

        double[][] p = gha.getProjection();
        double[][] t = Math.atamm(p);

        for (int i = 0; i < t.length; i++) {
            for (int j = 0; j < t[i].length; j++) {
                System.out.format("% .4f ", t[i][j]);
            }
            System.out.println();
        }

        double[][] s = Math.abtmm(Math.abmm(p, cov), p);
        double[] ev = new double[k];
        System.out.println("Relative error of eigenvalues:");
        for (int i = 0; i < k; i++) {
            ev[i] = Math.abs(eigenvalues[i] - s[i][i]) / eigenvalues[i];
            System.out.format("%.4f ", ev[i]);
        }
        System.out.println();

        for (int i = 0; i < k; i++) {
            assertTrue(ev[i] < 0.1);
        }

        double[][] lt = Math.transpose(loadings);
        double[] evdot = new double[k];
        System.out.println("Dot products of learned eigenvectors to true eigenvectors:");
        for (int i = 0; i < k; i++) {
            evdot[i] = Math.dot(lt[i], p[i]);
            System.out.format("%.4f ", evdot[i]);
        }
        System.out.println();

        for (int i = 0; i < k; i++) {
            assertTrue(Math.abs(1.0-Math.abs(evdot[i])) < 0.1);
        }
    }