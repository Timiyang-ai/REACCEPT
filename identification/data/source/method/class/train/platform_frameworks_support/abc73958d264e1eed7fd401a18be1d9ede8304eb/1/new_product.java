@Nullable
    List<T> getOutgoingEdges(@NonNull T node) {
        ArrayList<T> result = null;
        for (int i = 0, size = mGraph.size(); i < size; i++) {
            ArrayList<T> edges = mGraph.valueAt(i);
            if (edges != null && edges.contains(node)) {
                if (result == null) {
                    result = new ArrayList<>();
                }
                result.add(mGraph.keyAt(i));
            }
        }
        return result;
    }