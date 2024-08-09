public int getItemCount(int order) {
        //we go over all adapters and fetch all item sizes
        int size = 0;
        for (AbstractAdapter adapter : mAdapters.values()) {
            if (adapter.getOrder() < order) {
                size = adapter.getAdapterItemCount();
            } else {
                return size;
            }
        }
        return size;
    }