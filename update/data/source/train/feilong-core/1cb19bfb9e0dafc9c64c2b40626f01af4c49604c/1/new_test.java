@Test
    public void testFormatWithIncludes1(){
        User user1 = new User("feilong1", 24);
        user1.setNickNames(toArray("xin.jin", "shuai.ge"));
        User user2 = new User("feilong2", 240);
        user2.setNickNames(toArray("xin.jin", "shuai.ge"));

        List<User> list = toList(user1, user2);

        LOGGER.debug(JsonUtil.formatWithIncludes(list, "name", "age"));

        String[] array = { "id", "name" };
        LOGGER.debug(JsonUtil.formatWithIncludes(toArray(user1, user2), array));

        LOGGER.debug(JsonUtil.formatWithIncludes(toList("2,5,8", "2,5,9")));
        LOGGER.debug(JsonUtil.formatWithIncludes(user1));
    }