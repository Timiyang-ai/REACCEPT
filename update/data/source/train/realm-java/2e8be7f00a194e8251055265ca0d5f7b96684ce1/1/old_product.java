public void foreach(Callback<T> callback) {
        for (T pair : pairs) {
            if (cleared) {
                break;
            } else {
                Object observer = pair.observerRef.get();
                if (observer == null) {
                    pairs.remove(pair);
                } else if (!pair.removed) {
                    callback.onCalled(pair, observer);
                }
            }
        }
    }