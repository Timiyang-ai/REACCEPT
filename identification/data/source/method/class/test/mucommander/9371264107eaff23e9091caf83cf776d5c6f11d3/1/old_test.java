@Test
    public void testCapitalize() {
        assert "Bob".equals(StringUtils.capitalize("bob"));
        assert "Bob".equals(StringUtils.capitalize("BOB"));
        assert "Bob".equals(StringUtils.capitalize("bOB"));
        assert "Bob".equals(StringUtils.capitalize("bOB"));
        assert "Bob servant".equals(StringUtils.capitalize("Bob Servant"));

        assert "B".equals(StringUtils.capitalize("b"));

        assert "".equals(StringUtils.capitalize(""));
        assert "".equals(StringUtils.capitalize(null));

        assert "7".equals(StringUtils.capitalize("7"));
    }