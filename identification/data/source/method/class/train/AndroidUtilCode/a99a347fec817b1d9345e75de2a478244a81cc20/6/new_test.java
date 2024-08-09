    @Test
    public void reverse() {
        assertEquals("jknalb", StringUtils.reverse("blankj"));
        assertEquals("knalb", StringUtils.reverse("blank"));
        assertEquals("文中试测", StringUtils.reverse("测试中文"));
        assertEquals("", StringUtils.reverse(null));
    }