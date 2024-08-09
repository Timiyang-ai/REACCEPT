public int getPreItemCountByOrder(int order) {
        //if we are empty just return 0 count
        if (mGlobalSize == 0) {
            return 0;
        }

        int size = 0;

        //count the number of items before the adapter with the given order
        for (int i = 0; i < Math.min(order, mAdapters.size()); i++) {
            size = size + mAdapters.get(i).getAdapterItemCount();
        }

        //get the count of items which are before this order
        return size;
    }