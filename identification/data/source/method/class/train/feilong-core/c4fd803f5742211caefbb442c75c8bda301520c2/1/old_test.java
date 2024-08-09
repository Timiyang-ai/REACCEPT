@Test
    public void testGroupCount(){
        List<User> list = toList(//
                        new User("张飞", 20),
                        new User("关羽", 30),
                        new User("赵云", 50),
                        new User("刘备", 40),
                        new User("刘备", 30),
                        new User("赵云", 50));

        Predicate<User> comparatorPredicate = BeanPredicateUtil.comparatorPredicate("age", 30, Criterion.LESS);
        Map<String, Integer> map = AggregateUtil.groupCount(list, "name", comparatorPredicate);
        assertThat(map, allOf(hasEntry("刘备", 1), hasEntry("赵云", 2)));
    }