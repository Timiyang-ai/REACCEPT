public BitString[] learn(int size, int generation, double[][] x, double[] y, double[][] testx, double[] testy, RegressionMeasure measure, BiFunction<double[][], double[], Regression<double[]>> trainer) {
        if (size <= 0) {
            throw new IllegalArgumentException("Invalid population size: " + size);
        }
        
        if (generation <= 0) {
            throw new IllegalArgumentException("Invalid number of generations to go: " + generation);
        }
        
        if (x.length != y.length) {
            throw new IllegalArgumentException(String.format("The sizes of X and Y don't match: %d != %d", x.length, y.length));
        }

        if (testx.length != testy.length) {
            throw new IllegalArgumentException(String.format("The sizes of test X and Y don't match: %d != %d", testx.length, testy.length));
        }

        int p = x[0].length;
        RegressionFitness fitness = new RegressionFitness(trainer, measure, x, y, testx, testy);
        
        BitString[] seeds = new BitString[size];
        for (int i = 0; i < size; i++) {
            seeds[i] = new BitString(p, fitness, crossover, crossoverRate, mutationRate);
        }

        GeneticAlgorithm<BitString> ga = new GeneticAlgorithm<>(seeds, selection);
        ga.evolve(generation);       
        
        return seeds;        
    }