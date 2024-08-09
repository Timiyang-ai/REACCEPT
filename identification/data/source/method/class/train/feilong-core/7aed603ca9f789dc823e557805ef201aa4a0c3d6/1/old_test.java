@Test
    public void testCopyProperties(){
        Date now = new Date();
        String[] array = toArray("feilong", "飞天奔月", "venusdrogon");

        User oldUser = new User();
        oldUser.setId(5L);
        oldUser.setMoney(new BigDecimal(500000));
        oldUser.setDate(now);
        oldUser.setNickNames(array);

        User newUser = new User();
        PropertyUtil.copyProperties(newUser, oldUser, "date", "money", "nickNames");

        assertThat(newUser, allOf(//
                        hasProperty("money", equalTo(new BigDecimal(500000))),
                        hasProperty("date", is(now)),
                        hasProperty("nickNames", equalTo(array))
        //
        ));
    }