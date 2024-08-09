@Test
    public final void testGetFieldValueMap() throws IllegalArgumentException,IllegalAccessException{
        User user = new User(12L);
        LOGGER.info(JsonUtil.format(FieldUtil.getFieldValueMap(user)));
    }