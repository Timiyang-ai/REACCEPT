@Override
  public boolean isVisible() {
    Integer i = getObject(WebDriverLikeCommand.IS_VISIBLE);
    if (i == 1) {
      return true;
    } else {
      return false;
    }
  }