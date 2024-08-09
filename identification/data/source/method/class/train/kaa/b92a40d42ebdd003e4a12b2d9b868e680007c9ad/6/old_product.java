public List<ApplicationEventFamilyMapDto> getApplicationEventFamilyMapsByApplicationToken(String applicationToken) throws Exception {
        ParameterizedTypeReference<List<ApplicationEventFamilyMapDto>> typeRef = new ParameterizedTypeReference<List<ApplicationEventFamilyMapDto>>() {
        };
        ResponseEntity<List<ApplicationEventFamilyMapDto>> entity = restTemplate.exchange(restTemplate.getUrl() + "applicationEventMapsByAppToken/" +
                        applicationToken, HttpMethod.GET, null, typeRef);
        return entity.getBody();
    }