@Nullable
    ItemHolderInfo popFromPreLayout(ViewHolder vh) {
        int index = mLayoutHolderMap.indexOfKey(vh);
        if (index < 0) {
            return null;
        }
        final InfoRecord record = mLayoutHolderMap.valueAt(index);
        if (record != null && (record.flags & FLAG_PRE) != 0) {
            record.flags &= ~FLAG_PRE;
            final ItemHolderInfo info = record.preInfo;
            if (record.flags == 0) {
                mLayoutHolderMap.removeAt(index);
                InfoRecord.recycle(record);
            }
            return info;
        }
        return null;
    }