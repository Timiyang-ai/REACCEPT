@Test
    public void testGroupOne(){
        User zhangfei = new User("张飞", 23);
        User liubei25 = new User("刘备", 25);
        User liubei30 = new User("刘备", 30);
        List<User> list = toList(zhangfei, liubei25, liubei30);
        Map<String, User> map = CollectionsUtil.groupOne(list, "name");

        assertThat(map.keySet(), is(hasSize(2)));
        assertThat(map, allOf(//
                        hasEntry("张飞", zhangfei),
                        hasEntry("刘备", liubei25),
                        not(hasEntry("刘备", liubei30))));
    }