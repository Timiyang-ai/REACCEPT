public int getPosition(long identifier) {
        int position = 0;
        for (int i = 0, size = mAdapters.size(); i < size; i++) {
            IAdapter<Item> adapter = mAdapters.valueAt(i);
            if (adapter.getOrder() < 0) {
                continue;
            }

            int relativePosition = adapter.getAdapterPosition(identifier);
            if (relativePosition != -1) {
                if(mVerbose) Log.v(TAG, "getPosition");
                return position + relativePosition;
            }
            position = adapter.getAdapterItemCount();
        }

        return -1;
    }