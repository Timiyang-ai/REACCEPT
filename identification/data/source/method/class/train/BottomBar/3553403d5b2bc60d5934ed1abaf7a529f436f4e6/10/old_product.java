public void setItems(@MenuRes int menuRes) {
        clearItems();
        mItems = MiscUtils.inflateMenuFromResource((Activity) getContext(), menuRes);
        updateItems(mItems);
    }