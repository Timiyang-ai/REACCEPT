@Test
    public void getRootPackageTest() {
        String rootPackage = getRootPackage((byte) 1, CHILD_PACKAGE, DATE1);
        assertThat(rootPackage.equals(DEFAULT_BASE_PKG + PERIOD + VERSION_NUMBER
                + PERIOD + CHILD_WITH_PERIOD + PERIOD + DATE_WITH_REV1), is(true));
    }