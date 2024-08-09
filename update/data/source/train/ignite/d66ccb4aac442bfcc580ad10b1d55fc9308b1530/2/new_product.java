@Override public Double apply(Vector features) {
            double[] newFeatures = new double[featuresMapping.size()];
            featuresMapping.forEach((localId, featureVectorId) -> newFeatures[localId] = features.get(featureVectorId));
            return mdl.apply(new DenseLocalOnHeapVector(newFeatures));
        }