public void select(boolean considerSelectableFlag) {
        if (mPositionBasedStateManagement) {
            int length = getItemCount();
            for (int i = 0; i < length; i++) {
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