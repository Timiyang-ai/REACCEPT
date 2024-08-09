public RelativeInfo<Item> getRelativeInfo(int position) {
        if (position < 0) {
            return new RelativeInfo<>();
        }

        RelativeInfo<Item> relativeInfo = new RelativeInfo<>();
        IAdapter<Item> adapter = getAdapter(position);
        if (adapter != null) {
            relativeInfo.item = adapter.getAdapterItem(position - getItemCount(adapter.getOrder()));
            relativeInfo.adapter = adapter;
        }
        return relativeInfo;
    }