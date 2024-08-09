public RelativeInfo<Item> getRelativeInfo(int position) {
        if (position < 0 || position >= getItemCount()) {
            return new RelativeInfo<>();
        }

        RelativeInfo<Item> relativeInfo = new RelativeInfo<>();
        int index = floorIndex(mAdapterSizes, position);
        if (index != -1) {
            relativeInfo.item = mAdapterSizes.valueAt(index).getAdapterItem(position - mAdapterSizes.keyAt(index));
            relativeInfo.adapter = mAdapterSizes.valueAt(index);
            relativeInfo.position = position;
        }
        return relativeInfo;
    }