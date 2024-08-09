@Test
    public void getSumoURLForTopic() throws Exception {
        final Context context = RuntimeEnvironment.application;
        final String versionName = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;

        Locale.setDefault(Locale.GERMANY);
        assertEquals("https://support.mozilla.org/1/mobile/" + versionName + "/Android/de-DE/foobar",
                SupportUtils.getSumoURLForTopic(RuntimeEnvironment.application, "foobar"));

        Locale.setDefault(Locale.CANADA_FRENCH);
        assertEquals("https://support.mozilla.org/1/mobile/" + versionName + "/Android/fr-CA/foobar",
                SupportUtils.getSumoURLForTopic(RuntimeEnvironment.application, "foobar"));
    }