public void select(int position) {
        IItem item = getItem(position);
        if (item != null) {
            item.withSetSelected(true);
            mSelections.put(position, item);
        }
        notifyItemChanged(position);
    }