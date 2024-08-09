  private void setDisplayConfigurationTest() {
    System.out.println("==== Test setDisplayConfigurationTest ====");

    System.out.println("Testing normal setting");
    changeConfig(1.0f, 0f, 1f);

    System.out.println("Testing gamma settings");
    changeConfig(5.0f, 0f, 1f);
    changeConfig(0.5f, 0f, 1f);

    System.out.println("Testing brightness settings");
    changeConfig(1.0f, -1.0f, 1f);
    changeConfig(1.0f, -0.5f, 1f);
    changeConfig(1.0f, 0.5f, 1f);
    changeConfig(1.0f, 1.0f, 1f);

    System.out.println("Testing contrast settings");
    changeConfig(1.0f, 0f, 0f);
    changeConfig(1.0f, 0f, 0.5f);
    changeConfig(1.0f, 0f, 10000.0f);

    System.out.print("resetting...");
    try {
        Display.setFullscreen(false);
    } catch (LWJGLException e) {
        e.printStackTrace();
    }
    System.out.println("done");

    System.out.println("---- Test setDisplayConfigurationTest ----");
  }