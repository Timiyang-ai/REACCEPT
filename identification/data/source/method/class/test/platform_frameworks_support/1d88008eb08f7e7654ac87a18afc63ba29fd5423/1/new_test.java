    @Test
    public void setNavOptions() {
        NavAction action = new NavAction(DESTINATION_ID);
        NavOptions navOptions = new NavOptions.Builder().build();
        action.setNavOptions(navOptions);

        assertThat(action.getNavOptions(), is(navOptions));
    }