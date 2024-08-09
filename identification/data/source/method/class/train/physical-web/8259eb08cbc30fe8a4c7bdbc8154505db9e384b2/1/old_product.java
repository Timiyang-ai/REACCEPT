public static String getFatBeaconTitle(byte[] serviceData) {
    if (serviceData.length > 2) {
      String title = new String(Arrays.copyOfRange(serviceData, 3, serviceData.length)).trim();
      return  title.indexOf('\uFFFD') == -1 ? title : "";
    }
    return "";
  }