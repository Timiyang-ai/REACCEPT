public void setActiveTabAlpha(float alpha) {
        activeTabAlpha = alpha;

        batchPropertyApplier.applyToAllTabs(new BatchTabPropertyApplier.TabPropertyUpdater() {
            @Override
            public void update(BottomBarTab tab) {
                tab.setActiveAlpha(activeTabAlpha);
            }
        });
    }