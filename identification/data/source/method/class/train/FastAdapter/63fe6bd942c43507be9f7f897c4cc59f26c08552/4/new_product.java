public RelativeInfo<Item> getRelativeInfo(int position) {
        if (position < 0) {
            return new RelativeInfo<>();
        }

        RelativeInfo<Item> relativeInfo = new RelativeInfo<>();
        Map.Entry<Integer, IAdapter<Item>> entry = mAdapterSizes.floorEntry(position);
        if (entry != null) {
            relativeInfo.item = entry.getValue().getAdapterItem(position - entry.getKey());
            relativeInfo.adapter = entry.getValue();
        }
        return relativeInfo;
    }