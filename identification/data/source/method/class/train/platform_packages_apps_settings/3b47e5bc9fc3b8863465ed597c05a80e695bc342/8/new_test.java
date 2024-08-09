    @Test
    @Config(qualifiers = "mcc999")
    public void extractMetadata_shouldContainKeyAndControllerName()
            throws IOException, XmlPullParserException {
        List<Bundle> metadata = PreferenceXmlParserUtils.extractMetadata(mContext,
                R.xml.location_settings,
                MetadataFlag.FLAG_NEED_KEY | MetadataFlag.FLAG_NEED_PREF_CONTROLLER);

        assertThat(metadata).isNotEmpty();
        for (Bundle bundle : metadata) {
            assertThat(bundle.getString(PreferenceXmlParserUtils.METADATA_KEY)).isNotNull();
            assertThat(bundle.getString(PreferenceXmlParserUtils.METADATA_CONTROLLER)).isNotNull();
        }
    }