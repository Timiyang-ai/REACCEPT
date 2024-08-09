@Test
    public void populate(){
        User user = new User();
        user.setId(5L);

        Map<String, Long> properties = toMap("id", 8L);

        LOGGER.debug(JsonUtil.format(BeanUtil.populate(user, properties)));

        //********************************************************
        user = new User();
        user.setId(5L);

        BeanUtil.copyProperties(user, properties);
        LOGGER.debug(JsonUtil.format(user));
    }