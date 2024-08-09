@Test
    public void testGetLicenseText_4args() throws SQLException, AuthorizeException, IOException {
        //parameters for the test
        Locale locale = null;
        Collection collection = null;
        Item item = null;
        EPerson person = null;

        String template = "Template license: %1$s %2$s %3$s %5$s %6$s";
        String templateResult = "Template license: first name last name testgetlicensetext_4args@email.com  ";
        context.turnOffAuthorisationSystem();
        person = ePersonService.create(context);
        person.setFirstName(context, "first name");
        person.setLastName(context, "last name");
        person.setEmail("testGetLicenseText_4args@email.com");
        ePersonService.update(context, person);

        String defaultLicense = licenseService.getDefaultSubmissionLicense();

        context.turnOffAuthorisationSystem();
        //TODO: the tested method doesn't verify the input, will throw NPE if any parameter is null

        //testing for default license
        locale = Locale.ENGLISH;
        collection = collectionService.create(context, owningCommunity);
        item = installItemService.installItem(context, workspaceItemService.create(context, collection, false));
        assertThat("testGetLicenseText_5args 0", LicenseUtils.getLicenseText(locale, collection, item, person), equalTo(defaultLicense));

        locale = Locale.GERMAN;
        collection = collectionService.create(context, owningCommunity);
        item = installItemService.installItem(context, workspaceItemService.create(context, collection, false));
        assertThat("testGetLicenseText_5args 1", LicenseUtils.getLicenseText(locale, collection, item, person), equalTo(defaultLicense));

        //test collection template
        locale = Locale.ENGLISH;
        collection = collectionService.create(context, owningCommunity);
        collection.setLicense(context, template);
        item = installItemService.installItem(context, workspaceItemService.create(context, collection, false));
        assertThat("testGetLicenseText_5args 3", LicenseUtils.getLicenseText(locale, collection, item, person), equalTo(templateResult));

        locale = Locale.GERMAN;
        collection = collectionService.create(context, owningCommunity);
        collection.setLicense(context, template);
        item = installItemService.installItem(context, workspaceItemService.create(context, collection, false));
        assertThat("testGetLicenseText_5args 4", LicenseUtils.getLicenseText(locale, collection, item, person), equalTo(templateResult));

        context.restoreAuthSystemState();
    }