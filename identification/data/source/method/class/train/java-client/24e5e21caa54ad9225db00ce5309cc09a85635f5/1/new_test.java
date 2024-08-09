    @Test public void replaceValueTest() {
        String originalValue = "original value";

        Activity activity = new Activity("io.appium.android.apis", ".view.Controls1");
        driver.startActivity(activity);
        AndroidElement editElement = driver
            .findElementByAndroidUIAutomator("resourceId(\"io.appium.android.apis:id/edit\")");
        editElement.sendKeys(originalValue);
        assertEquals(originalValue, editElement.getText());
        String replacedValue = "replaced value";
        editElement.replaceValue(replacedValue);
        assertEquals(replacedValue, editElement.getText());
    }