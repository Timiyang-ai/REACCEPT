@Test
    public void testLearn2() {
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

        String[] symbols = {"0", "1"};
        String[][] sequences = new String[5000][];
        int[][] labels = new int[5000][];
        for (int i = 0; i < sequences.length; i++) {
            sequences[i] = new String[30 * (Math.randomInt(5) + 1)];
            labels[i] = new int[sequences[i].length];
            int state = (int) initial.rand();
            sequences[i][0] = symbols[(int) emission[state].rand()];
            labels[i][0] = state;
            for (int j = 1; j < sequences[i].length; j++) {
                state = (int) transition[state].rand();
                sequences[i][j] = symbols[(int) emission[state].rand()];
                labels[i][j] = state;
            }
        }

        HMM<String> hmm = new HMM(sequences, labels);
        System.out.println(hmm);

        double[] pi2 = {0.55, 0.45};
        double[][] a2 = {{0.7, 0.3}, {0.15, 0.85}};
        double[][] b2 = {{0.45, 0.55}, {0.3, 0.7}};
        HMM<String> init = new HMM<>(pi2, a2, b2, symbols);
        HMM<String> result = init.learn(sequences, 100);
        System.out.println(result);
    }