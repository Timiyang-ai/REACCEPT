public RelativeInfo<Item> getRelativeInfo(int position) {
        if (position < 0) {
            return new RelativeInfo<>();
        }

        RelativeInfo<Item> relativeInfo = new RelativeInfo<>();
        IAdapter adapter = getAdapter(position);
        if (adapter != null) {
            Item item = (Item) adapter.getAdapterItem(position - getItemCount(adapter.getOrder()));
            relativeInfo.item = item;
            relativeInfo.adapter = adapter;
        }
        return relativeInfo;
    }