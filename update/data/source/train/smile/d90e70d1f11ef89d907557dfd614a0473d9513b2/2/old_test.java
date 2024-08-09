@Test
    public void testLearn() {
        System.out.println("learn");
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

        HMM hmm = new HMM(sequences, labels);
        System.out.println(hmm);

        double[] pi2 = {0.55, 0.45};
        double[][] a2 = {{0.7, 0.3}, {0.15, 0.85}};
        double[][] b2 = {{0.45, 0.55}, {0.3, 0.7}};
        HMM init = new HMM(pi2, a2, b2);
        HMM result = init.learn(sequences, 100);
        System.out.println(result);
    }