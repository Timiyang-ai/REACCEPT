@Override
    public int getItemCount() {
        //we go over all adapters and fetch all item sizes
        int size = 0;
        int length = mAdapters.size();
        for (int i = 0; i < length; i++) {
            IAdapter adapter = mAdapters.valueAt(i);
            if (adapter.getOrder() < 0) {
                continue;
            }

            size = size + adapter.getAdapterItemCount();
        }
        return size;
    }