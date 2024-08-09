@NonNull
    public static List<Bundle> extractMetadata(Context context, @XmlRes int xmlResId, int flags)
            throws IOException, XmlPullParserException {
        final List<Bundle> metadata = new ArrayList<>();
        if (xmlResId <= 0) {
            Log.d(TAG, xmlResId + " is invalid.");
            return metadata;
        }
        final XmlResourceParser parser = context.getResources().getXml(xmlResId);

        int type;
        while ((type = parser.next()) != XmlPullParser.END_DOCUMENT
                && type != XmlPullParser.START_TAG) {
            // Parse next until start tag is found
        }
        final int outerDepth = parser.getDepth();

        do {
            if (type != XmlPullParser.START_TAG) {
                continue;
            }
            final String nodeName = parser.getName();
            if (!hasFlag(flags, MetadataFlag.FLAG_INCLUDE_PREF_SCREEN)
                    && TextUtils.equals(PREF_SCREEN_TAG, nodeName)) {
                continue;
            }
            if (!SUPPORTED_PREF_TYPES.contains(nodeName) && !nodeName.endsWith("Preference")) {
                continue;
            }
            final Bundle preferenceMetadata = new Bundle();
            final AttributeSet attrs = Xml.asAttributeSet(parser);
            if (hasFlag(flags, MetadataFlag.FLAG_NEED_PREF_TYPE)) {
                preferenceMetadata.putString(METADATA_PREF_TYPE, nodeName);
            }
            if (hasFlag(flags, MetadataFlag.FLAG_NEED_KEY)) {
                preferenceMetadata.putString(METADATA_KEY, getDataKey(context, attrs));
            }
            if (hasFlag(flags, MetadataFlag.FLAG_NEED_PREF_CONTROLLER)) {
                preferenceMetadata.putString(METADATA_CONTROLLER, getController(context, attrs));
            }
            if (hasFlag(flags, MetadataFlag.FLAG_NEED_PREF_TITLE)) {
                preferenceMetadata.putString(METADATA_TITLE, getDataTitle(context, attrs));
            }
            if (hasFlag(flags, MetadataFlag.FLAG_NEED_PREF_SUMMARY)) {
                preferenceMetadata.putString(METADATA_SUMMARY, getDataSummary(context, attrs));
            }
            if (hasFlag(flags, MetadataFlag.FLAG_NEED_PREF_ICON)) {
                preferenceMetadata.putInt(METADATA_ICON, getDataIcon(context, attrs));
            }
            metadata.add(preferenceMetadata);
        } while ((type = parser.next()) != XmlPullParser.END_DOCUMENT
                && (type != XmlPullParser.END_TAG || parser.getDepth() > outerDepth));
        parser.close();
        return metadata;
    }