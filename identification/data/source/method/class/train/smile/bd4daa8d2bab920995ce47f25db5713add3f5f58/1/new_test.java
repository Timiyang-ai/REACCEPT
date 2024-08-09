@Test
    public void test() {
        System.out.println("GHA");

        int k = 3;
        double[] mu = MathEx.colMeans(USArrests);
        DenseMatrix cov = Matrix.of(MathEx.cov(USArrests));
        for (int i = 0; i < USArrests.length; i++) {
           MathEx.sub(USArrests[i], mu);
        }

        double r = 0.00001;
        GHA gha = new GHA(4, k, r);
        for (int iter = 1, t = 0; iter <= 1000; iter++) {
            double error = 0.0;
            for (int i = 0; i < USArrests.length; i++, t++) {
                error += gha.update(USArrests[i]);
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

        double[][] lt = MathEx.transpose(loadings);
        double[] evdot = new double[k];
        double[][] pa = p.toArray();
        System.out.println("Dot products of learned eigenvectors to true eigenvectors:");
        for (int i = 0; i < k; i++) {
            evdot[i] = MathEx.dot(lt[i], pa[i]);
            System.out.format("%.4f ", evdot[i]);
        }
        System.out.println();

        for (int i = 0; i < k; i++) {
            assertTrue(Math.abs(1.0- Math.abs(evdot[i])) < 0.1);
        }
    }