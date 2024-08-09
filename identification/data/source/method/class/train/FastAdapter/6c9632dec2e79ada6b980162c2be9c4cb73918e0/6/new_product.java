public void select(boolean considerSelectableFlag) {
        if (mPositionBasedStateManagement) {
            for (int i = 0, size = getItemCount(); i < size; i++) {
                select(i, false, considerSelectableFlag);
            }
        } else {
            for (IItem item : AdapterUtil.getAllItems(this)) {
                if (considerSelectableFlag && !item.isSelectable()) {
                    continue;
                }
                item.withSetSelected(true);
            }
            notifyDataSetChanged();
        }
    }