@Override
    public int getItemCount() {
        //we go over all adapters and fetch all item sizes
        int size = 0;
        for (IAdapter adapter : mAdapters.values()) {
            size = adapter.getAdapterItemCount();
        }
        return size;
    }