    @Test
    public void isValidTest_bound_binder() {
        binder.forField(nameField)
                .withValidator(Validator.from(
                        name -> !name.equals("fail field validation"), ""))
                .bind(Person::getFirstName, Person::setFirstName);

        binder.withValidator(Validator.from(
                person -> !person.getFirstName().equals("fail bean validation"),
                ""));

        binder.setBean(item);

        assertTrue(binder.isValid());

        nameField.setValue("fail field validation");
        assertFalse(binder.isValid());

        nameField.setValue("");
        assertTrue(binder.isValid());

        nameField.setValue("fail bean validation");
        assertFalse(binder.isValid());
    }