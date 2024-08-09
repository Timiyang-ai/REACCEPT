public int getItemCount(int order) {
        //we go over all adapters and fetch all item sizes
        int size = 0;

        int length = mAdapters.size();
        for (int i = 0; i < length; i++) {
            IAdapter adapter = mAdapters.valueAt(i);
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