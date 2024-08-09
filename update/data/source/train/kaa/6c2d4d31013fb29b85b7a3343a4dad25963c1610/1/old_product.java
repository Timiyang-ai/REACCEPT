public void addEventClassFamilySchema(String eventClassFamilyId, String schemaResource) throws Exception {
        MultiValueMap<String, Object> params = new LinkedMultiValueMap<>();
        params.add("eventClassFamilyId", eventClassFamilyId);
        params.add("file", getFileResource(schemaResource));
        restTemplate.postForLocation(restTemplate.getUrl() + "addEventClassFamilySchema", params);
    }