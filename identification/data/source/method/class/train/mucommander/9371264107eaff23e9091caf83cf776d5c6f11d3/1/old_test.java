@Test
    public void testFlatten() {
        assert "a b c".equals(StringUtils.flatten(new String[]{"a", "b", "c"}));
        assert "a b c".equals(StringUtils.flatten(new String[]{"a", "b", "c"}, " "));

        assert "a*b*c".equals(StringUtils.flatten(new String[]{"a", "b", "c"}, "*"));

        assert "a*c".equals(StringUtils.flatten(new String[]{"a", "", "c"}, "*"));

        assert "a*c".equals(StringUtils.flatten(new String[]{"a", null, "c"}, "*"));

        assert "b".equals(StringUtils.flatten(new String[]{null, "b", null}, "*"));

        assert "".equals(StringUtils.flatten(new String[]{null, null, null}, "*"));

        assert null == StringUtils.flatten(null, "*");
    }