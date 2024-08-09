@Override public LabeledVector<L> apply(K key, V value) {
        L lbl = isLabeled() ? label(labelCoord(key, value), key, value) : zero();

        List<C> allCoords = null;
        if (useAllValues) {
            allCoords = allCoords(key, value).stream()
                .filter(coord -> !coord.equals(labelCoord) && !excludedCoords.contains(coord))
                .collect(Collectors.toList());
        }

        int vectorLen = useAllValues ? allCoords.size() : extractionCoordinates.size();
        A.ensure(vectorLen >= 0, "vectorLength >= 0");

        List<C> coordinatesForExtraction = useAllValues ? allCoords : extractionCoordinates;
        Vector vector = createVector(vectorLen);
        for (int i = 0; i < coordinatesForExtraction.size(); i++) {
            Serializable feature = feature(coordinatesForExtraction.get(i), key, value);
            if (feature != null)
                vector.setRaw(i, feature);
        }
        return new LabeledVector<>(vector, lbl);
    }