@Test
    public void testSum(){
        List<User> list = toList(//
                        new User(2L),
                        new User(5L),
                        new User(5L));
        assertEquals(new BigDecimal(12L), AggregateUtil.sum(list, "id"));
    }