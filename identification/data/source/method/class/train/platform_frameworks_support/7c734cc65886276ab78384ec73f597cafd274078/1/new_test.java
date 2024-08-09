    @Test
    public void getIcon() {
        Person person = new Person.Builder().setIcon(TEST_ICON).build();
        assertEquals(TEST_ICON, person.getIcon());
    }