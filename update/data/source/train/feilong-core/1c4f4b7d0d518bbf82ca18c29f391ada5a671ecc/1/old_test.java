@Test
    public void toList(){
        User user1 = new User();
        user1.setId(1L);
        User user2 = new User();
        user2.setId(2L);

        User[] users = { user1, user2 };

        List<User> list = ConvertUtil.toList(users);
        LOGGER.info(JsonUtil.format(list));
    }