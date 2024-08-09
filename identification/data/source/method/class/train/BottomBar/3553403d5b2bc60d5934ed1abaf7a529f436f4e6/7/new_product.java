public void setItems(BottomBarTab... bottomBarTabs) {
        clearItems();
        mItems = bottomBarTabs;
        updateItems(mItems);
    }