@Test
    public void testFormatWithIncludes1(){
        User user1 = new User("feilong1", 24);
        user1.setId(8L);

        User user2 = new User("feilong2", 240);

        List<User> list = toList(user1, user2);

        String[] array = { "id", "name" };
        LOGGER.debug(JsonUtil.formatWithIncludes(list, array));
        LOGGER.debug(JsonUtil.formatWithIncludes(toArray(user1, user2), array));

        LOGGER.debug(JsonUtil.formatWithIncludes(toList("2,5,8", "2,5,9")));
        LOGGER.debug(JsonUtil.formatWithIncludes(user1));
    }