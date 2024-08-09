@Test
    public void testLearn() {
        System.out.println("learn");

        int k = 3;
        double[] mu = Math.colMeans(USArrests);
        DenseMatrix cov = Matrix.newInstance(Math.cov(USArrests));
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

        DenseMatrix p = gha.getProjection();
        DenseMatrix t = p.ata();

        for (int i = 0; i < t.nrows(); i++) {
            for (int j = 0; j < t.ncols(); j++) {
                System.out.format("% .4f ", t.get(i, j));
            }
            System.out.println();
        }

        DenseMatrix s = p.abmm(cov).abtmm(p);
        double[] ev = new double[k];
        System.out.println("Relative error of eigenvalues:");
        for (int i = 0; i < k; i++) {
            ev[i] = Math.abs(eigenvalues[i] - s.get(i, i)) / eigenvalues[i];
            System.out.format("%.4f ", ev[i]);
        }
        System.out.println();

        for (int i = 0; i < k; i++) {
            assertTrue(ev[i] < 0.1);
        }

        double[][] lt = Math.transpose(loadings);
        double[] evdot = new double[k];
        double[][] pa = p.array();
        System.out.println("Dot products of learned eigenvectors to true eigenvectors:");
        for (int i = 0; i < k; i++) {
            evdot[i] = Math.dot(lt[i], pa[i]);
            System.out.format("%.4f ", evdot[i]);
        }
        System.out.println();

        for (int i = 0; i < k; i++) {
            assertTrue(Math.abs(1.0-Math.abs(evdot[i])) < 0.1);
        }
    }