public Item getItem(int position) {
        //if we are out of range just return null
        if (position < 0 || position >= mGlobalSize) {
            return null;
        }
        //now get the adapter which is responsible for the given position
        int index = floorIndex(mAdapterSizes, position);
        return mAdapterSizes.valueAt(index).getAdapterItem(position - mAdapterSizes.keyAt(index));
    }