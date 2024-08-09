public double[] featuresInSortedOrder(int k, int featureId) {
        return features[idx[k][featureId]];
    }