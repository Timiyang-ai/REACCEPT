@Test
    public void createClass()
    {
	//TODO: rewrite the test to not rely on the breadcrumb based on parent/child mechanism.
        getUtil().setHierarchyMode("parentchild");
        try {
            String spaceName = getTestClassName();
            String className = RandomStringUtils.randomAlphabetic(5);
            String classDocName = className + "Class";
            String classTitle = className + " Class";
            String pageName = getTestMethodName();
            // Make sure the document doesn't exist.
            getUtil().deletePage(spaceName, pageName);

            // Create the class document.
            DataTypesPage dataTypesPage = DataTypesPage.gotoPage();
            String dataTypesPageTitle = dataTypesPage.getDocumentTitle();
            Assert.assertTrue(dataTypesPage.isClassListed("XWiki", "XWikiRights"));
            Assert.assertFalse(dataTypesPage.isClassListed(spaceName, classDocName));
            ClassSheetPage classSheetPage = dataTypesPage.createClass(spaceName, className);
            Assert.assertEquals(classTitle, classSheetPage.getDocumentTitle());
            Assert.assertTrue(classSheetPage.hasBreadcrumbContent(dataTypesPageTitle, false));

            // Add a property.
            ClassEditPage classEditor = classSheetPage.clickDefineClassLink();
            classEditor.addProperty("color", "String").setPrettyName("Your favorite color");
            classEditor.clickSaveAndView();

            // Add a new property.
            classEditor = classSheetPage.clickEditClassLink();
            classEditor.addProperty("age", "Number").setPrettyName("Your current age");
            classEditor.clickSaveAndView();

            // We have to wait for the page to load because Selenium doesn't do it all the time when we click on Save & View
            // (even if the Save & View button triggers a plain form submit; there must be something with the JavaScript
            // code that is executed on submit that interferes with Selenium).
            classSheetPage.waitUntilPageIsLoaded();

            // Assert that the properties are listed.
            Assert.assertTrue(classSheetPage.hasProperty("color", "Your favorite color", "String"));
            Assert.assertTrue(classSheetPage.hasProperty("age", "Your current age", "Number"));

            // Create and bind a sheet.
            classSheetPage = classSheetPage.clickCreateSheetButton().clickBindSheetLink();
            ViewPage sheetPage = classSheetPage.clickSheetLink();
            Assert.assertEquals(className + " Sheet", sheetPage.getDocumentTitle());
            sheetPage.clickBreadcrumbLink(classTitle);

            // Create the template.
            classSheetPage = classSheetPage.clickCreateTemplateButton().clickAddObjectToTemplateLink();
            ViewPage templatePage = classSheetPage.clickTemplateLink();
            Assert.assertEquals(className + " Template", templatePage.getDocumentTitle());
            // The default edit button should take us to the In-line edit mode.
            templatePage.edit();
            InlinePage editPage = new InlinePage();
            editPage.setValue("color", "red");
            editPage.setValue("age", "13");
            editPage.clickSaveAndContinue();
            editPage.clickBreadcrumbLink(classTitle);

            // Create a document based on the class template.
            Assert.assertEquals(spaceName, classSheetPage.getSpaceNameInput().getAttribute("value"));
            editPage = classSheetPage.createNewDocument(spaceName, pageName);

            Assert.assertEquals(pageName, editPage.getDocumentTitle());
            Assert.assertEquals("red", editPage.getValue("color"));
            Assert.assertEquals("13", editPage.getValue("age"));

            editPage.setValue("color", "blue");
            editPage.setValue("age", "27");
            ViewPage viewPage = editPage.clickSaveAndView();

            Assert.assertEquals(pageName, viewPage.getDocumentTitle());
            Assert.assertEquals("Your favorite color\nblue\nYour current age\n27", viewPage.getContent());
            viewPage.clickBreadcrumbLink(classTitle);

            // Assert the created document is listed.
            Assert.assertTrue(classSheetPage.hasDocument(pageName));
        } finally {
            getUtil().setHierarchyMode("reference");
        }
    }