@Test
    public void testGetFieldValueMap() throws IllegalAccessException{
        User user = new User(12L);
        LOGGER.info(JsonUtil.format(FieldUtil.getAllFieldNameAndValueMap(user)));
    }