public LabeledVector<L> apply(K key, V value) {
        L lbl = isLabeled() ? label(labelCoord(key, value), key, value) : zero();

        List<C> allCoords = null;
        if (useAllValues) {
            allCoords = allCoords(key, value).stream()
                .filter(coord -> !coord.equals(labelCoord) && !excludedCoords.contains(coord))
                .collect(Collectors.toList());
        }

        int vectorLength = useAllValues ? allCoords.size() : extractionCoordinates.size();
        A.ensure(vectorLength >= 0, "vectorLength >= 0");

        List<C> coordinatesForExtraction = useAllValues ? allCoords : extractionCoordinates;
        Vector vector = createVector(vectorLength);
        for (int i = 0; i < coordinatesForExtraction.size(); i++) {
            Double feature = feature(coordinatesForExtraction.get(i), key, value);
            if (feature != null)
                vector.set(i, feature);
        }
        return new LabeledVector<>(vector, lbl);
    }