@Test
    public void getRootPackageTest() throws ParseException {
        conflictResolver.setPrefixForIdentifier(null);
        Date date = simpleDateFormat.parse(DATE1);
        String rootPackage = getRootPackage((byte) 1, CHILD_PACKAGE, date, conflictResolver);
        assertThat(rootPackage.equals(DEFAULT_BASE_PKG + PERIOD + VERSION_NUMBER
                + PERIOD + CHILD_WITH_PERIOD + PERIOD + DATE_WITH_REV1), is(true));
    }