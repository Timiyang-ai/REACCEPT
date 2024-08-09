public void setBadgeBackgroundColor(@ColorInt int color) {
        badgeBackgroundColor = color;

        batchPropertyApplier.applyToAllTabs(new BatchTabPropertyApplier.TabPropertyUpdater() {
            @Override
            public void update(BottomBarTab tab) {
                tab.setBadgeBackgroundColor(badgeBackgroundColor);
            }
        });
    }