public static HttpResponse getNowBlock(String httpNode) {
    httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 2000);
    httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 2000);
    try {
      String getAccountUrl = "http://" + httpNode + "/wallet/getnowblock";
      httppost = new HttpPost(getAccountUrl);
      httppost.setHeader("Content-type", "application/json; charset=utf-8");
      httppost.setHeader("Connection", "Close");
      response = httpClient.execute(httppost);
    } catch (Exception e) {
      e.printStackTrace();
      httppost.releaseConnection();
      return null;
    }
    httppost.releaseConnection();
    return response;
  }