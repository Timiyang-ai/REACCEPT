    @Test
    public void withValidator_doesNotDisablesDefaulNullRepresentation() {
        String nullRepresentation = "foo";
        binder.forField(nameField).withNullRepresentation(nullRepresentation)
                .withValidator(new NotEmptyValidator<>(""))
                .bind(Person::getFirstName, Person::setFirstName);
        item.setFirstName(null);
        binder.setBean(item);

        assertEquals(nullRepresentation, nameField.getValue());

        String newValue = "bar";
        nameField.setValue(newValue);
        assertEquals(newValue, item.getFirstName());
    }