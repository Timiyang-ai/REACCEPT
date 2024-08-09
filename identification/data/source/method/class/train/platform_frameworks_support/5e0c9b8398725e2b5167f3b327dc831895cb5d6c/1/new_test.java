    @Test
    public void toBuilder() {
        Person person = new Person.Builder()
                .setImportant(TEST_IS_IMPORTANT)
                .setBot(TEST_IS_BOT)
                .setKey(TEST_KEY)
                .setUri(TEST_URI)
                .setIcon(TEST_ICON)
                .setName(TEST_NAME)
                .build();
        Person result = person.toBuilder().build();

        assertEquals(TEST_NAME, result.getName());
        assertEquals(TEST_URI, result.getUri());
        assertEquals(TEST_KEY, result.getKey());
        assertEquals(TEST_IS_BOT, result.isBot());
        assertEquals(TEST_IS_IMPORTANT, result.isImportant());
        assertEquals(TEST_ICON.toBundle().toString(), result.getIcon().toBundle().toString());
    }