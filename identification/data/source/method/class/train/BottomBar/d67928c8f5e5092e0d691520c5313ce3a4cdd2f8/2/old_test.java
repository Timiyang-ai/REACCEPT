    @Test
    @UiThreadTest
    public void setBadgeBackgroundColor_UpdatesColor() {
        BottomBarTab inActiveTab = bottomBar.getTabAtPosition(1);
        inActiveTab.setBadgeCount(3);

        int previousBadgeColor = inActiveTab.getBadgeBackgroundColor();
        int testColor = Color.GREEN;
        assertNotEquals(testColor, previousBadgeColor);

        bottomBar.setBadgeBackgroundColor(testColor);
        assertEquals(testColor, inActiveTab.getBadgeBackgroundColor());
    }