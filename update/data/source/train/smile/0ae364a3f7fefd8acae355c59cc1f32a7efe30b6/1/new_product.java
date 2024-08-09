public double[][] rand(int n) {
        double[][] data = new double[n][];
        for (int i = 0; i < n; i++) {
            data[i] = rand();
        }
        return data;
    }