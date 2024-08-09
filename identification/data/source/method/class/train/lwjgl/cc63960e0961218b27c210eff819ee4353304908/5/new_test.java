  private void setDisplayModeTest() throws LWJGLException {
    DisplayMode mode = null;
    DisplayMode[] modes = null;

    System.out.println("==== Test setDisplayMode ====");
    System.out.println("Retrieving available displaymodes");
    modes = Display.getAvailableDisplayModes();

    // no modes check
    if (modes == null) {
      System.out.println("FATAL: unable to find any modes!");
      System.exit(-1);
    }

    // find a mode
    System.out.print("Looking for 640x480...");
	  for ( DisplayMode mode1 : modes ) {
		  if ( mode1.getWidth() == 640 &&
		       mode1.getHeight() == 480 ) {
			  mode = mode1;
			  System.out.println("found!");
			  break;
		  }
	  }

    // no mode check
    if (mode == null) {
      System.out.println("error\nFATAL: Unable to find basic mode.");
      System.exit(-1);
    }

    // change to mode, and wait a bit
    System.out.print("Changing to mode...");
    try {
      Display.setDisplayMode(mode);
      Display.setFullscreen(true);
      Display.create();
    } catch (Exception e) {
      System.out.println("error\nFATAL: Error setting mode");
      System.exit(-1);
    }
    System.out.println("done");

    System.out.println("Resolution: " +
        Display.getDisplayMode().getWidth()      + "x" +
        Display.getDisplayMode().getHeight()     + "x" +
        Display.getDisplayMode().getBitsPerPixel()      + "@" +
        Display.getDisplayMode().getFrequency()  + "Hz");

    pause(5000);

    // reset
    System.out.print("Resetting mode...");
    try {
        Display.setFullscreen(false);
    } catch (LWJGLException e) {
        e.printStackTrace();
    }
    System.out.println("done");

    System.out.println("---- Test setDisplayMode ----");
  }