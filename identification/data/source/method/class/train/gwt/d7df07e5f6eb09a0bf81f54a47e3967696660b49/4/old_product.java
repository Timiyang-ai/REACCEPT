public static String getTemplateContent(String path) {
    if (DesignTimeUtils.isDesignTime()) {
      return System.getProperty("gwt.UiBinder.designTime " + path);
    }
    return null;
  }