public void addAll(NavGraph other) {
        for (int i = 0, size = other.mNodes.size(); i < size; i++) {
            mNodes.put(other.mNodes.keyAt(i), other.mNodes.valueAt(i));
        }
    }