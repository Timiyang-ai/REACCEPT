@Override public Double apply(Vector v) {
        if (!datasets.isEmpty()) {
            List<LabeledVector> neighbors = findKNearestNeighbors(v);

            return classify(neighbors, v, stgy);
        }
        else
            throw new IllegalStateException("The train kNN dataset is null");
    }