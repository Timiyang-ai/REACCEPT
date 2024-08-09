public int getPreItemCount(int position) {
        //if we are empty just return 0 count
        if (mGlobalSize == 0) {
            return 0;
        }

        //get the count of items which are before this order
        return mAdapterSizes.keyAt(floorIndex(mAdapterSizes, position));
    }