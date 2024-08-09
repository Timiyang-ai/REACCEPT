public static HttpResponse getBrokerageFromSolidity(String httpSolidityNode, byte[] address) {
    try {
      String requestUrl = "http://" + httpSolidityNode + "/walletsolidity/getBrokerage";
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