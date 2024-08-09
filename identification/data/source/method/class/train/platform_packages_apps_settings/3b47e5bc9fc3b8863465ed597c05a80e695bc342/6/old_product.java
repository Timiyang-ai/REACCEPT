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
            final TypedArray preferenceAttributes = context.obtainStyledAttributes(attrs,
                    R.styleable.Preference);

            if (hasFlag(flags, MetadataFlag.FLAG_NEED_PREF_TYPE)) {
                preferenceMetadata.putString(METADATA_PREF_TYPE, nodeName);
            }
            if (hasFlag(flags, MetadataFlag.FLAG_NEED_KEY)) {
                preferenceMetadata.putString(METADATA_KEY, getKey(preferenceAttributes));
            }
            if (hasFlag(flags, MetadataFlag.FLAG_NEED_PREF_CONTROLLER)) {
                preferenceMetadata.putString(METADATA_CONTROLLER,
                        getController(preferenceAttributes));
            }
            if (hasFlag(flags, MetadataFlag.FLAG_NEED_PREF_TITLE)) {
                preferenceMetadata.putString(METADATA_TITLE, getTitle(preferenceAttributes));
            }
            if (hasFlag(flags, MetadataFlag.FLAG_NEED_PREF_SUMMARY)) {
                preferenceMetadata.putString(METADATA_SUMMARY, getSummary(preferenceAttributes));
            }
            if (hasFlag(flags, MetadataFlag.FLAG_NEED_PREF_ICON)) {
                preferenceMetadata.putInt(METADATA_ICON, getIcon(preferenceAttributes));
            }
            if (hasFlag(flags, MetadataFlag.FLAG_NEED_PLATFORM_SLICE_FLAG)) {
                preferenceMetadata.putBoolean(METADATA_PLATFORM_SLICE_FLAG,
                        getPlatformSlice(preferenceAttributes));
            }
            if (hasFlag(flags, MetadataFlag.FLAG_NEED_KEYWORDS)) {
                preferenceMetadata.putString(METADATA_KEYWORDS, getKeywords(preferenceAttributes));
            }
            if (hasFlag(flags, MetadataFlag.FLAG_NEED_SEARCHABLE)) {
                preferenceMetadata.putBoolean(METADATA_SEARCHABLE,
                        isSearchable(preferenceAttributes));
            }
            if (hasFlag(flags, MetadataFlag.FLAG_ALLOW_DYNAMIC_SUMMARY_IN_SLICE)) {
                preferenceMetadata.putBoolean(METADATA_ALLOW_DYNAMIC_SUMMARY_IN_SLICE,
                        isDynamicSummaryAllowed(preferenceAttributes));
            }
            metadata.add(preferenceMetadata);

            preferenceAttributes.recycle();
        } while ((type = parser.next()) != XmlPullParser.END_DOCUMENT
                && (type != XmlPullParser.END_TAG || parser.getDepth() > outerDepth));
        parser.close();
        return metadata;
    }