@Test
    public void testDescribe(){
        Date now = new Date();

        User user = new User();
        user.setId(5L);
        user.setDate(now);

        assertThat(PropertyUtil.describe(user), allOf(hasEntry("id", (Object) 5L), hasEntry("date", (Object) now)));
        //LOGGER.debug("map:{}", JsonUtil.format(PropertyUtil.describe(user)));

        List<User> list = ConvertUtil.toList(user);
        LOGGER.debug("map:{}", JsonUtil.format(PropertyUtil.describe(new BigDecimal(5L))));
        LOGGER.debug("map:{}", JsonUtil.format(PropertyUtil.describe("123456")));
        LOGGER.debug("map:{}", JsonUtil.format(PropertyUtil.describe(list)));
        LOGGER.debug("map:{}", JsonUtil.format(PropertyUtil.describe(new HashMap())));
    }