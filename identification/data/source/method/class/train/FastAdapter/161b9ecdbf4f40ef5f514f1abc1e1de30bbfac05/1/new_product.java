public IAdapter<Item> getAdapter(int position) {
        //if we are out of range just return null
        if (position < 0 || position >= mGlobalSize) {
            return null;
        }
        if(mVerbose) Log.v(TAG, "getAdapter");
        //now get the adapter which is responsible for the given position
        return mAdapterSizes.valueAt(floorIndex(mAdapterSizes, position));
    }