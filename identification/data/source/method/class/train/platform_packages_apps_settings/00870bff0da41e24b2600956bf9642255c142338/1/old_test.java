    @Test
    public void getCallingAppPackageName_returnsPackageName() throws Exception {
        when(mActivityService.getLaunchedFromPackage(mActivityToken))
                .thenReturn(PACKAGE_NAME);

        assertThat(getCallingAppPackageName(mActivityToken)).isEqualTo(PACKAGE_NAME);
    }