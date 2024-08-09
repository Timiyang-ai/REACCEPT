public double[] featuresInSortedOrder(int k, int featureId) {
        return features[index[k][featureId]];
    }