public static HttpResponse getAccountNet(String httpNode, byte[] queryAddress) {
    httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 2000);
    httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 2000);
    try {
      String getAccountUrl = "http://" + httpNode + "/wallet/getaccountnet";
      httppost = new HttpPost(getAccountUrl);
      httppost.setHeader("Content-type", "application/json; charset=utf-8");
      httppost.setHeader("Connection", "Close");
      JsonObject userBaseObj2 = new JsonObject();
      userBaseObj2.addProperty("address", ByteArray.toHexString(queryAddress));
      StringEntity entity = new StringEntity(userBaseObj2.toString(), Charset.forName("UTF-8"));
      entity.setContentEncoding("UTF-8");
      entity.setContentType("application/json");
      httppost.setEntity(entity);
      response = httpClient.execute(httppost);
    } catch (Exception e) {
      e.printStackTrace();
      httppost.releaseConnection();
      return null;
    }
    httppost.releaseConnection();
    return response;
  }