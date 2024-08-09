  private void initialize() {
    // create display and opengl
    setupDisplay(false);

    try {
      Keyboard.create();
    } catch (Exception e) {
      e.printStackTrace();
      System.exit(-1);
    }
  }