public DecisionTreeData filter(TreeFilter filter) {
        int size = 0;

        double[][] features = getFeatures();
        double[] labels = getLabels();
        for (int i = 0; i < features.length; i++)
            if (filter.test(features[i]))
                size++;

        double[][] newFeatures = new double[size][];
        double[] newLabels = new double[size];

        int ptr = 0;

        for (int i = 0; i < features.length; i++) {
            if (filter.test(features[i])) {
                newFeatures[ptr] = features[i];
                newLabels[ptr] = labels[i];

                ptr++;
            }
        }

        return new DecisionTreeData(newFeatures, newLabels, buildIdx);
    }