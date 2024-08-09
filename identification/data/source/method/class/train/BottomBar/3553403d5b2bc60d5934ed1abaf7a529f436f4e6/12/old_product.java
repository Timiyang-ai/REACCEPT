public void setItems(BottomBarTab... bottomBarTabs) {
        clearItems();

        int index = 0;
        int biggestWidth = 0;
        mIsShiftingMode = MAX_FIXED_TAB_COUNT < bottomBarTabs.length;

        if (mIsShiftingMode) {
            mItemContainer.setBackgroundColor(mPrimaryColor);
        }

        View[] viewsToAdd = new View[bottomBarTabs.length];

        for (BottomBarTab bottomBarTab : bottomBarTabs) {
            ViewGroup bottomBarView = (ViewGroup) View.inflate(mContext, mIsShiftingMode ?
                    R.layout.bb_bottom_bar_item_shifting : R.layout.bb_bottom_bar_item_fixed, null);

            ImageView icon = (ImageView) bottomBarView.findViewById(R.id.bb_bottom_bar_icon);
            TextView title = (TextView) bottomBarView.findViewById(R.id.bb_bottom_bar_title);

            icon.setImageDrawable(bottomBarTab.getIcon(mContext));
            title.setText(bottomBarTab.getTitle(mContext));

            if (mIsShiftingMode) {
                icon.setColorFilter(mWhiteColor);
            }

            if (index == mCurrentTabPosition) {
                selectTab(bottomBarView, false);
            } else {
                unselectTab(bottomBarView, false);
            }

            if (bottomBarView.getWidth() > biggestWidth) {
                biggestWidth = bottomBarView.getWidth();
            }

            bottomBarView.setOnClickListener(this);
            viewsToAdd[index] = bottomBarView;
            index++;
        }

        int screenWidth = MiscUtils.getScreenWidth(mContext);
        int proposedItemWidth = Math.min(
                MiscUtils.dpToPixel(mContext, screenWidth / bottomBarTabs.length),
                mMaxFixedItemWidth
        );

        LinearLayout.LayoutParams params = new LinearLayout
                .LayoutParams(proposedItemWidth, LinearLayout.LayoutParams.WRAP_CONTENT);

        for (View bottomBarView : viewsToAdd) {
            bottomBarView.setLayoutParams(params);
            mItemContainer.addView(bottomBarView);
        }
    }