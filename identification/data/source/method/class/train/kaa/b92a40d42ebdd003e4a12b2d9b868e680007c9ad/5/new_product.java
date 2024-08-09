public List<AefMapInfoDto> getEventClassFamiliesByApplicationToken(String applicationToken)
      throws Exception {
    ResponseEntity<List<AefMapInfoDto>> entity = restTemplate.exchange(
        restTemplate.getUrl() + "eventClassFamilies/" + applicationToken,
        HttpMethod.GET, null, new ParameterizedTypeReference<List<AefMapInfoDto>>() {});
    return entity.getBody();
  }