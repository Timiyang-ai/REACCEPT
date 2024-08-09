  private static void setIcon(MaterialButton materialButton, int measureSpec, Drawable drawable) {
    materialButton.setIcon(drawable);
    materialButton.setIconGravity(MaterialButton.ICON_GRAVITY_START);
    materialButton.measure(measureSpec, measureSpec);
  }