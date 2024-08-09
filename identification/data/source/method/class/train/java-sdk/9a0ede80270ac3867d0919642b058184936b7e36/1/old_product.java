public ServiceCall<Customization> createCustomization(String name, SpeechModel baseModel, String description) {
    Validator.notNull(name, "name cannot be null");
    Validator.notNull(baseModel, "baseModel cannot be null");

    RequestBuilder requestBuilder = RequestBuilder.post(PATH_CUSTOMIZATIONS);

    Customization newCustomization = new Customization();
    newCustomization.setBaseModelName(baseModel.getName());
    newCustomization.setDescription(description);
    newCustomization.setName(name);
    requestBuilder.bodyContent(GSON.toJson(newCustomization), HttpMediaType.APPLICATION_JSON);
    return createServiceCall(requestBuilder.build(), ResponseConverterUtils.getObject(Customization.class));
  }