@Test(dependsOnMethods = "init")
    public void updatePreference() throws Exception {
        final PreferenceMgmtService preferenceMgmtService = getPreferenceMgmtService();
        final PreferenceQueryService preferenceQueryService = getPreferenceQueryService();
        JSONObject preference = preferenceQueryService.getPreference();

        Assert.assertEquals(preference.getString(Preference.BLOG_TITLE),
                Preference.Default.DEFAULT_BLOG_TITLE);

        preference.put(Preference.BLOG_TITLE, "updated blog title");
        preferenceMgmtService.updatePreference(preference);

        preference = preferenceQueryService.getPreference();
        Assert.assertEquals(preference.getString(Preference.BLOG_TITLE), "updated blog title");
    }