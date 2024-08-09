@Test
    public void populate(){
        User user = new User();
        user.setId(5L);

        Map<String, Long> properties = toMap("id", 8L);

        BeanUtil.populate(user, properties);
        LOGGER.debug(JsonUtil.format(user));

        //********************************************************
        user = new User();
        user.setId(5L);

        BeanUtil.copyProperties(user, properties);
        LOGGER.debug(JsonUtil.format(user));
    }