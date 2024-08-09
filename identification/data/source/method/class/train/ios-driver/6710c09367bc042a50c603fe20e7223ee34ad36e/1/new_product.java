@Override
  public boolean isVisible() {
    Integer i = getObject(WebDriverLikeCommand.DISPLAYED);
    if (i == 1) {
      return true;
    } else {
      return false;
    }
  }