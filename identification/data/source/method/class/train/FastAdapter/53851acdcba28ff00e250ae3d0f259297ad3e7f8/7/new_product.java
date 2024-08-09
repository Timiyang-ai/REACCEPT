public int getItemCount(int order) {
        //we go over all adapters and fetch all item sizes
        int size = 0;
        for (IAdapter adapter : mAdapters.values()) {
            if (adapter.getOrder() < 0) {
                continue;
            }

            if (adapter.getOrder() < order) {
                size = adapter.getAdapterItemCount();
            } else {
                return size;
            }
        }
        return size;
    }