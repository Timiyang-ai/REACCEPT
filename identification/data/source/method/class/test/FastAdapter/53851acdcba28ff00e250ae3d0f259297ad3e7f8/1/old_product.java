@Override
    public int getItemCount() {
        //we go over all adapters and fetch all item sizes
        int size = 0;
        for (AbstractAdapter adapter : mAdapters.values()) {
            size = adapter.getAdapterItemCount();
        }
        return size;
    }