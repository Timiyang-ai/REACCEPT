public String getOAuthQueryString(ProtectedResourceDetails details, OAuthConsumerToken accessToken, URL url, String httpMethod, Map<String, String> additionalParameters) {
    Map<String, Set<CharSequence>> oauthParams = loadOAuthParameters(details, url, accessToken, httpMethod, additionalParameters);

    StringBuilder queryString = new StringBuilder();
    if (details.isAcceptsAuthorizationHeader()) {
      //if the resource accepts the auth header, remove any parameters that will go in the header (don't pass them redundantly in the query string).
      for (OAuthConsumerParameter oauthParam : OAuthConsumerParameter.values()) {
        oauthParams.remove(oauthParam.toString());
      }

      if (additionalParameters != null) {
        for (String additionalParam : additionalParameters.keySet()) {
          oauthParams.remove(additionalParam);
        }
      }
    }

    Iterator<String> parametersIt = oauthParams.keySet().iterator();
    while (parametersIt.hasNext()) {
      String parameter = parametersIt.next();
      queryString.append(parameter);
      Set<CharSequence> values = oauthParams.get(parameter);
      if (values != null) {
        Iterator<CharSequence> valuesIt = values.iterator();
        while (valuesIt.hasNext()) {
          CharSequence parameterValue = valuesIt.next();
          if (parameterValue != null) {
            queryString.append('=').append(parameterValue);
          }
          if (valuesIt.hasNext()) {
            queryString.append('&').append(parameter);
          }
        }
      }
      if (parametersIt.hasNext()) {
        queryString.append('&');
      }
    }

    return queryString.toString();
  }