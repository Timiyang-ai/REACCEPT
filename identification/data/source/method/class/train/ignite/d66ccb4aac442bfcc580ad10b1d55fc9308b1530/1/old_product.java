@Override public Double apply(double[] features) {
            double[] newFeatures = new double[featuresMapping.size()];
            featuresMapping.forEach((localId, featureVectorId) -> newFeatures[localId] = features[featureVectorId]);
            return model.apply(newFeatures);
        }