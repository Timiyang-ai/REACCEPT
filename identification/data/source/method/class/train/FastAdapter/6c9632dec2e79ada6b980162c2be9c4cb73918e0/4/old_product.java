public void select() {
        if (mPositionBasedStateManagement) {
            int length = getItemCount();
            for (int i = 0; i < length; i++) {
                select(i);
            }
        } else {
            for (IItem item : AdapterUtil.getAllItems(this)) {
                item.withSetSelected(true);
            }
            notifyDataSetChanged();
        }
    }