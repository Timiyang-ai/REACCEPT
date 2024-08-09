public List<ApplicationEventFamilyMapDto> getApplicationEventFamilyMapsByApplicationToken(
      String applicationToken) throws Exception {
    ResponseEntity<List<ApplicationEventFamilyMapDto>> entity = restTemplate.exchange(
        restTemplate.getUrl() + "applicationEventMaps/" + applicationToken,
        HttpMethod.GET, null,
        new ParameterizedTypeReference<List<ApplicationEventFamilyMapDto>>() {});
    return entity.getBody();
  }