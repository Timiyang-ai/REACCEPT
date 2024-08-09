public ClassSheetPage createClass(String spaceName, String className)
    {
        this.documentPicker.setParent(spaceName);
        this.documentPicker.setName(className);
        this.createClassButton.click();
        new WikiEditPage().clickSaveAndView();
        return new ClassSheetPage();
    }