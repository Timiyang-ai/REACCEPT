@Test
    public void testGroupCount(){
        List<User> list = new ArrayList<User>();
        list.add(new User("张飞", 20));
        list.add(new User("关羽", 30));
        list.add(new User("刘备", 40));
        list.add(new User("赵云", 50));

        Map<String, Integer> map = CollectionsUtil.groupCount(list, "name", new Predicate<User>(){

            @Override
            public boolean evaluate(User user){
                return user.getAge() > 30;
            }
        });
        LOGGER.debug(JsonUtil.format(map));
    }