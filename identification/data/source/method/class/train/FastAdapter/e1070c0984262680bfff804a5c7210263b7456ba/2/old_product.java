public int getPreItemCountByOrder(int order) {
        //if we are empty just return 0 count
        if (mGlobalSize == 0) {
            return 0;
        }

        int size = 0;

        //count the number of items before the adapter with the given order
        for (IAdapter<Item> adapter : mAdapters.values()) {
            if (adapter.getOrder() == order) {
                return size;
            } else {
                size = size + adapter.getAdapterItemCount();
            }
        }

        //get the count of items which are before this order
        return size;
    }