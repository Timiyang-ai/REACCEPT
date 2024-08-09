@Test(dataProvider = "capitalize")
    public void testCapitalize(String input, String expected) {
        assert expected.equals(StringUtils.capitalize(input));
    }