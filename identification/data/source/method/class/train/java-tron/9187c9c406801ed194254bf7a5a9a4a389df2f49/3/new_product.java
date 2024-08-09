public static HttpResponse getNowBlock(String httpNode) {
    try {
      String requestUrl = "http://" + httpNode + "/wallet/getnowblock";
      response = createConnect(requestUrl);
    } catch (Exception e) {
      e.printStackTrace();
      httppost.releaseConnection();
      return null;
    }
    return response;
  }