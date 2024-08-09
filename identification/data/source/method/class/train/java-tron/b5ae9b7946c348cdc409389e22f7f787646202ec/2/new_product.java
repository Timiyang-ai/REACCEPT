public static HttpResponse updateBrokerage(String httpNode, byte[] ownerAddress, Long brokerage,
      String fromKey) {
    try {
      final String requestUrl = "http://" + httpNode + "/wallet/updateBrokerage";
      JsonObject userBaseObj2 = new JsonObject();
      userBaseObj2.addProperty("owner_address", ByteArray.toHexString(ownerAddress));
      userBaseObj2.addProperty("brokerage", brokerage);
      logger.info("userBaseObj2:" + userBaseObj2);
      response = createConnect(requestUrl, userBaseObj2);
      transactionString = EntityUtils.toString(response.getEntity());
      transactionSignString = gettransactionsign(httpNode, transactionString, fromKey);
      response = broadcastTransaction(httpNode, transactionSignString);
    } catch (Exception e) {
      e.printStackTrace();
      httppost.releaseConnection();
      return null;
    }
    return response;
  }