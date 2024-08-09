public RelativeInfo<Item> getRelativeInfo(int position) {
        if (position < 0) {
            return new RelativeInfo<>();
        }

        IAdapter adapter = getAdapter(position);
        Item item = (Item) adapter.getAdapterItem(position - getItemCount(adapter.getOrder()));
        RelativeInfo<Item> relativeInfo = new RelativeInfo<>();
        relativeInfo.item = item;
        relativeInfo.adapter = adapter;
        return relativeInfo;
    }