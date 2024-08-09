public static KMeans lloyd(double[][] data, int k) {
        return lloyd(data, k, 100, 1E-4);
    }