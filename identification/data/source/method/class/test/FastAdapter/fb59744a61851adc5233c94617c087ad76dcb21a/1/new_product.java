public Item getItem(int position) {
        //if we are out of range just return null
        if (position < 0 || position >= mGlobalSize) {
            return null;
        }
        //now get the adapter which is responsible for the given position
        Map.Entry<Integer, IAdapter<Item>> entry = mAdapterSizes.floorEntry(position);
        return entry.getValue().getAdapterItem(position - entry.getKey());
    }