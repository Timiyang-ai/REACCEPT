@Override
    public int getItemCount() {
        //we go over all adapters and fetch all item sizes
        int size = 0;
        for (IAdapter adapter : mAdapters.values()) {
            if (adapter.getOrder() < 0) {
                continue;
            }

            size = adapter.getAdapterItemCount();
        }
        return size;
    }