    @Test
    public void test_collectItems_nullList() {
        final Optional<ArrayList<AppInfo>> appInfoList = Optional.empty();
        CustomPackageList.appInfoList = appInfoList;
        final CharSequence[] result = CustomPackageList.collectItems();
        assertThat(result.length, is(0));
    }