public ClassSheetPage createClass(String spaceName, String className)
    {
        spaceNameInput.clear();
        spaceNameInput.sendKeys(spaceName);
        classNameInput.clear();
        classNameInput.sendKeys(className);
        createClassButton.click();
        new WikiEditPage().clickSaveAndView();
        return new ClassSheetPage();
    }