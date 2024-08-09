@SimpleProperty(
    description = "Returns the sprite's heading in degrees above the positive " +
    "x-axis.  Zero degrees is toward the right of the screen; 90 degrees is toward the " +
    "top of the screen.")
  public double Heading() {
    return userHeading;
  }