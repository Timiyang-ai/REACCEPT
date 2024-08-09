public void addEventClassFamilyVersion(String eventClassFamilyId,
                                         EventClassFamilyVersionDto eventClassFamilyVersion)
      throws Exception {
    MultiValueMap<String, Object> params = new LinkedMultiValueMap<>();
    params.add("eventClassFamilyId", eventClassFamilyId);
    params.add("eventClassFamilyVersion", eventClassFamilyVersion);
    restTemplate.postForLocation(restTemplate.getUrl() + "addEventClassFamilyVersion", params);
  }