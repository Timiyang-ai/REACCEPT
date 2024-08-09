@Test
    public void testFit() {
        System.out.println("fit");
        MathEx.setSeed(19650218); // to get repeatable results.

        EmpiricalDistribution initial = new EmpiricalDistribution(pi);

        EmpiricalDistribution[] transition = new EmpiricalDistribution[a.length];
        for (int i = 0; i < transition.length; i++) {
            transition[i] = new EmpiricalDistribution(a[i]);
        }

        EmpiricalDistribution[] emission = new EmpiricalDistribution[b.length];
        for (int i = 0; i < emission.length; i++) {
            emission[i] = new EmpiricalDistribution(b[i]);
        }

        int[][] sequences = new int[5000][];
        int[][] labels = new int[5000][];
        for (int i = 0; i < sequences.length; i++) {
            sequences[i] = new int[30 * (MathEx.randomInt(5) + 1)];
            labels[i] = new int[sequences[i].length];
            int state = (int) initial.rand();
            sequences[i][0] = (int) emission[state].rand();
            labels[i][0] = state;
            for (int j = 1; j < sequences[i].length; j++) {
                state = (int) transition[state].rand();
                sequences[i][j] = (int) emission[state].rand();
                labels[i][j] = state;
            }
        }

        HMM model = HMM.fit(sequences, labels);
        System.out.println(model);

        double[] expPi2 = {0.5076, 0.4924};
        double[][] expA2 = {{0.8002, 0.1998}, {0.1987, 0.8013}};
        double[][] expB2 = {{0.5998, 0.4002}, {0.4003, 0.5997}};

        double[] pi2 = model.getInitialStateProbabilities();
        for (int i = 0; i < pi.length; i++) {
            assertEquals(expPi2[i], pi2[i], 1E-4);
        }

        DenseMatrix a2 = model.getStateTransitionProbabilities();
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                assertEquals(expA2[i][j], a2.get(i, j), 1E-4);
            }
        }

        DenseMatrix b2 = model.getSymbolEmissionProbabilities();
        for (int i = 0; i < b.length; i++) {
            for (int j = 0; j < b[i].length; j++) {
                assertEquals(expB2[i][j], b2.get(i, j), 1E-4);
            }
        }
    }