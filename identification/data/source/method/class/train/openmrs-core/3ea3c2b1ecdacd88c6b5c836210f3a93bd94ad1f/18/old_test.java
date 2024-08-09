    @Test
    public void handle_shouldIgnoreBlankAddresses() {
        PersonSaveHandler handler = new PersonSaveHandler();
        Person person = new Person();
        PersonName personName = new PersonName("John","","Smith");
        person.addName(personName);
        person.setGender("M");
        PersonAddress personAddress = new PersonAddress();
        personAddress.setAddress1("     ");
        person.addAddress(personAddress);

        handler.handle(person,null,null,null);
        Assert.assertEquals(0,person.getAddresses().size());
    }