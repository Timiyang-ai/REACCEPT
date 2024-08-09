public static String getSumoURLForTopic(final Context context, final SumoTopic topic) {
        final String escapedTopic = getEncodedTopicUTF8(topic.topicStr);
        final String appVersion = getAppVersion(context);
        final String osTarget = "Android";
        final String langTag = Locales.getLanguageTag(Locale.getDefault());
        return "https://support.mozilla.org/1/mobile/" + appVersion + "/" + osTarget + "/" + langTag + "/" + escapedTopic;
    }