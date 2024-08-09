    @Test
    public void setReadOnly_unboundBinder() {
        binder.forField(nameField).bind(Person::getFirstName,
                Person::setFirstName);

        binder.forField(ageField);

        binder.setReadOnly(true);

        assertTrue(nameField.isReadOnly());
        assertFalse(ageField.isReadOnly());

        binder.setReadOnly(false);

        assertFalse(nameField.isReadOnly());
        assertFalse(ageField.isReadOnly());
    }