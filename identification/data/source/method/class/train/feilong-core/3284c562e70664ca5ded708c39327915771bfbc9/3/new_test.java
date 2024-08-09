@Test
    public void testSelect(){
        User zhangfei = new User("张飞", 23);
        User guanyu = new User("关羽", 24);
        User liubei = new User("刘备", 25);
        List<User> list = toList(zhangfei, guanyu, liubei);
        assertThat(
                        CollectionsUtil.select(list, "name", toList("张飞", "刘备")),
                        allOf(hasItem(zhangfei), hasItem(liubei), not(hasItem(guanyu))));
    }