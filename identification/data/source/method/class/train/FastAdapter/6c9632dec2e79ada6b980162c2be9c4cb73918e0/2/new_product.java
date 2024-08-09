public void select(int position, boolean fireEvent) {
        Item item = getItem(position);
        if (item != null) {
            item.withSetSelected(true);

            if (mPositionBasedStateManagement) {
                mSelections.add(position);
            }
        }
        notifyItemChanged(position);

        if (mOnClickListener != null && fireEvent) {
            mOnClickListener.onClick(null, getAdapter(position), item, position);
        }
    }