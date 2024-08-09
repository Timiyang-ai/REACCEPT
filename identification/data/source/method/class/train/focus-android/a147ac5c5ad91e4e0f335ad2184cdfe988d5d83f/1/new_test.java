@Test
    public void getSumoURLForTopic() throws Exception {
        final Context context = RuntimeEnvironment.application;
        final String versionName = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;

        final SupportUtils.SumoTopic testTopic = SupportUtils.SumoTopic.TRACKERS;
        final String testTopicStr = testTopic.topicStr;

        Locale.setDefault(Locale.GERMANY);
        assertEquals("https://support.mozilla.org/1/mobile/" + versionName +"/Android/de-DE/" + testTopicStr,
                SupportUtils.getSumoURLForTopic(RuntimeEnvironment.application, testTopic));

        Locale.setDefault(Locale.CANADA_FRENCH);
        assertEquals("https://support.mozilla.org/1/mobile/" + versionName + "/Android/fr-CA/" + testTopicStr,
                SupportUtils.getSumoURLForTopic(RuntimeEnvironment.application, testTopic));
    }