@SimpleProperty(
      category = PropertyCategory.BEHAVIOR)
  @DesignerProperty(
      editorType = PropertyTypeConstants.PROPERTY_TYPE_FLOAT,
      defaultValue = DEFAULT_HEADING + "")
  public void Heading(double userHeading) {
    this.userHeading = userHeading;
    // Flip, because y increases in the downward direction on Android canvases
    heading = -userHeading;
    headingRadians = Math.toRadians(heading);
    headingCos = Math.cos(headingRadians);
    headingSin = Math.sin(headingRadians);
    // changing the heading needs to force a redraw for image sprites that rotate
    registerChange();
  }