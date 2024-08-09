@Test
    public void testDescribe(){
        Date now = new Date();

        User user = new User();
        user.setId(5L);
        user.setDate(now);

        assertThat(PropertyUtil.describe(user), allOf(hasEntry("id", (Object) 5L), hasEntry("date", (Object) now)));
        assertThat(PropertyUtil.describe(user, "date", "id"), allOf(hasEntry("date", (Object) now), hasEntry("id", (Object) 5L)));
        assertThat(PropertyUtil.describe(user, "date"), hasEntry("date", (Object) now));
    }