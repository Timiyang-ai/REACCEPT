    @Test
    @Config(qualifiers = "mcc999")
    public void getSliceKeys_indexesDatabase() {
        // Force new indexing
        Locale.setDefault(new Locale("ca"));
        final SearchFeatureProvider provider = new SearchFeatureProviderImpl();
        final SlicesFeatureProvider sliceProvider = spy(new SlicesFeatureProviderImpl());
        final FakeFeatureFactory factory = FakeFeatureFactory.setupForTest();
        factory.searchFeatureProvider = provider;
        factory.slicesFeatureProvider = sliceProvider;
        // Fake the indexable list.
        provider.getSearchIndexableResources().getProviderValues().clear();
        provider.getSearchIndexableResources().getProviderValues().add(
                FakeIndexProvider.class);

        final SlicesDatabaseAccessor accessor = new SlicesDatabaseAccessor(mContext);
        final List<String> keys = accessor.getSliceKeys(true);

        assertThat(keys).isNotEmpty();
    }