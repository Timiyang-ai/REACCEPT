    @Test
    public void setActiveTabAlpha_LeavesOtherValuesIntact() {
        bottomBar.setActiveTabAlpha(0.2f);

        BottomBarTab activeTab = bottomBar.getCurrentTab();
        assertEquals(INACTIVE_TAB_ALPHA, activeTab.getInActiveAlpha(), 0);
        assertEquals(0.2f, activeTab.getActiveAlpha(), 0);
        assertEquals(INACTIVE_TAB_COLOR, activeTab.getInActiveColor());
        assertEquals(ACTIVE_TAB_COLOR, activeTab.getActiveColor());
        assertEquals(BACKGROUND_COLOR, activeTab.getBarColorWhenSelected());
        assertEquals(BADGE_BACKGROUND_COLOR, activeTab.getBadgeBackgroundColor());
        assertEquals(TITLE_TEXT_APPEARANCE, activeTab.getTitleTextAppearance());
        assertEquals(TYPEFACE, activeTab.getTitleTypeFace());
    }