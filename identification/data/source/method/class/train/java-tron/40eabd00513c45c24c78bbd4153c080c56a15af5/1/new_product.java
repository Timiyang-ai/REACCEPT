public static HttpResponse sendCoin(String httpNode, byte[] fromAddress, byte[] toAddress, 
      Long amount, String fromKey) {
    try {
      final String requestUrl = "http://" + httpNode + "/wallet/createtransaction";
      JsonObject userBaseObj2 = new JsonObject();
      userBaseObj2.addProperty("to_address", ByteArray.toHexString(toAddress));
      userBaseObj2.addProperty("owner_address", ByteArray.toHexString(fromAddress));
      userBaseObj2.addProperty("amount", amount);
      response = createConnect(requestUrl, userBaseObj2);
      transactionString = EntityUtils.toString(response.getEntity());
      transactionSignString = gettransactionsign(httpNode,transactionString,fromKey);
      response = broadcastTransaction(httpNode,transactionSignString);
    } catch (Exception e) {
      e.printStackTrace();
      httppost.releaseConnection();
      return null;
    }
    return response;
  }