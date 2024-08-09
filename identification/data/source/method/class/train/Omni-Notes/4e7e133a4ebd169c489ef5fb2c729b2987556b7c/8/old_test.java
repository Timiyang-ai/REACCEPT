  @Test
  public void getBitmapFromAttachment_backgroundThread () {
    Bitmap bmp = BitmapHelper.getBitmapFromAttachment(testContext, attachment, 100, 100);
    assertNotEquals("Thread MUST be a background one", Looper.getMainLooper(), Looper.myLooper());
    assertNotNull("Bitmap should not be null", bmp);
  }