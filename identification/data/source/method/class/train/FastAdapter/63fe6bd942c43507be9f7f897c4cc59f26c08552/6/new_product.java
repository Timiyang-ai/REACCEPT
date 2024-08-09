public RelativeInfo<Item> getRelativeInfo(int position) {
        if (position < 0) {
            return new RelativeInfo<>();
        }

        RelativeInfo<Item> relativeInfo = new RelativeInfo<>();
        IAdapter adapter = getAdapter(position);
        if (adapter != null) {
            relativeInfo.item = (Item) adapter.getAdapterItem(position - getItemCount(adapter.getOrder()));
            relativeInfo.adapter = adapter;
        }
        return relativeInfo;
    }