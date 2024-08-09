public static SparseAnnotations union(SparseAnnotations annotations,
                                          SparseAnnotations sparseAnnotations) {

        if (sparseAnnotations == null || sparseAnnotations.keys().isEmpty()) {
            return annotations;
        }

        final HashMap<String, String> newMap;
        if (annotations instanceof DefaultAnnotations) {
            newMap = copy(((DefaultAnnotations) annotations).map);
        } else {
            newMap = new HashMap<>(annotations.keys().size() +
                                   sparseAnnotations.keys().size());
            putAllSparseAnnotations(newMap, annotations);
        }

        putAllSparseAnnotations(newMap, sparseAnnotations);
        return new DefaultAnnotations(newMap);
    }