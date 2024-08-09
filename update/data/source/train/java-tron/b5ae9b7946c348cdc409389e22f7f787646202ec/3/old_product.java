public static HttpResponse getBrokerage(String httpNode, byte[] address) {
    try {
      String requestUrl = "http://" + httpNode + "/wallet/getBrokerage";
      JsonObject userBaseObj2 = new JsonObject();
      userBaseObj2.addProperty("address", ByteArray.toHexString(address));
      response = createConnect(requestUrl, userBaseObj2);
    } catch (Exception e) {
      e.printStackTrace();
      httppost.releaseConnection();
      return null;
    }
    return response;
  }