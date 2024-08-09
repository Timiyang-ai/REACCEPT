@Test
    public void testGetLicenseText_4args() throws SQLException, AuthorizeException
    {
        //parameters for the test
        Locale locale = null;
        Collection collection = null;
        Item item = null;
        EPerson person = null;

        String template = "Template license: %1$s %2$s %3$s %5$s %6$s";
        String templateResult = "Template license: first name last name test@email.com  ";
        String defaultLicense = LicenseManager.getDefaultSubmissionLicense();

        context.turnOffAuthorisationSystem();
        //TODO: the tested method doesn't verify the input, will throw NPE if any parameter is null

        //testing for default license
        locale = Locale.ENGLISH;
        collection = Collection.create(context);
        item = Item.create(context);
        person = EPerson.create(context);
        person.setFirstName("first name");
        person.setLastName("last name");
        person.setEmail("test@email.com");
        assertThat("testGetLicenseText_5args 0", LicenseUtils.getLicenseText(locale, collection, item, person), equalTo(defaultLicense));

        locale = Locale.GERMAN;
        collection = Collection.create(context);
        item = Item.create(context);
        person = EPerson.create(context);
        person.setFirstName("first name");
        person.setLastName("last name");
        person.setEmail("test@email.com");
        assertThat("testGetLicenseText_5args 1", LicenseUtils.getLicenseText(locale, collection, item, person), equalTo(defaultLicense));

        //test collection template
        locale = Locale.ENGLISH;
        collection = Collection.create(context);
        collection.setLicense(template);
        item = Item.create(context);
        person = EPerson.create(context);
        person.setFirstName("first name");
        person.setLastName("last name");
        person.setEmail("test@email.com");
        assertThat("testGetLicenseText_5args 3", LicenseUtils.getLicenseText(locale, collection, item, person), equalTo(templateResult));

        locale = Locale.GERMAN;
        collection = Collection.create(context);
        collection.setLicense(template);
        item = Item.create(context);
        person = EPerson.create(context);
        person.setFirstName("first name");
        person.setLastName("last name");
        person.setEmail("test@email.com");
        assertThat("testGetLicenseText_5args 4", LicenseUtils.getLicenseText(locale, collection, item, person), equalTo(templateResult));

        context.restoreAuthSystemState();
    }