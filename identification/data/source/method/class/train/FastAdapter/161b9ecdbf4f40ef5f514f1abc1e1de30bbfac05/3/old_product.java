public IAdapter getAdapter(int position) {
        int currentCount = 0;
        int length = mAdapters.size();
        for (int i = 0; i < length; i++) {
            IAdapter adapter = mAdapters.valueAt(i);
            if (adapter.getOrder() < 0) {
                continue;
            }

            if (currentCount <= position && currentCount + adapter.getAdapterItemCount() > position) {
                return adapter;
            }
            currentCount = currentCount + adapter.getAdapterItemCount();
        }
        return null;
    }