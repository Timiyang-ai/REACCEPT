public static String getFatBeaconTitle(byte[] serviceData) {
    if (serviceData.length > 2) {
      byte[] bytes = Arrays.copyOfRange(serviceData, 3, serviceData.length);
      String title = new String(bytes, Charset.forName("UTF-8")).trim();
      return  title.indexOf('\uFFFD') == -1 ? title : "";
    }
    return "";
  }