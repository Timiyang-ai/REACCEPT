public void select(int position, boolean fireEvent, boolean considerSelectableFlag) {
        Item item = getItem(position);

        if (item == null) {
            return;
        }
        if (considerSelectableFlag && !item.isSelectable()) {
            return;
        }

        item.withSetSelected(true);

        if (mPositionBasedStateManagement) {
            mSelections.add(position);
        }

        notifyItemChanged(position);

        if (mSelectionListener != null)
            mSelectionListener.onSelectionChanged(item, true);

        if (mOnClickListener != null && fireEvent) {
            mOnClickListener.onClick(null, getAdapter(position), item, position);
        }
    }