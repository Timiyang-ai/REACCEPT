    @Test
    void camelToUnderlineTest() {
        String s = "userId";
        Assertions.assertEquals("user_id", StringUtils.camelToUnderline(s));
    }