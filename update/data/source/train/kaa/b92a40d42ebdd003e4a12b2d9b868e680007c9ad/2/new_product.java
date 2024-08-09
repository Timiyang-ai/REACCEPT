public List<EcfInfoDto> getVacantEventClassFamiliesByApplicationToken(String applicationToken)
      throws Exception {
    ResponseEntity<List<EcfInfoDto>> entity = restTemplate.exchange(
        restTemplate.getUrl() + "vacantEventClassFamilies/" + applicationToken,
        HttpMethod.GET, null, new ParameterizedTypeReference<List<EcfInfoDto>>() {});
    return entity.getBody();
  }