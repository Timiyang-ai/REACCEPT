  @Test
  public void isVisibleTests() {

    WebElement navBar = driver.findElement(By.tagName("UIANavigationBar"));
    Assert.assertTrue(navBar.isDisplayed(), "nav bar");

    List<WebElement> imgs = navBar.findElements(By.className("UIAImage"));

    Assert.assertEquals(imgs.size(), 2, "number of images in the menu");

    for (WebElement el : imgs) {
      Assert.assertFalse(el.isDisplayed(), "image");
    }

    WebElement text = navBar.findElement(By.className("UIAStaticText"));
    Assert.assertTrue(text.isDisplayed(), "nav bar text");
  }