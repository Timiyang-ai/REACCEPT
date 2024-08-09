public ServiceCall<DetectedFaces> detectFaces(DetectFacesOptions detectFacesOptions) {
    Validator.notNull(detectFacesOptions, "detectFacesOptions cannot be null");
    Validator.isTrue((detectFacesOptions.imagesFile() != null) || (detectFacesOptions.url() != null),
        "At least one of imagesFile or url must be supplied.");
    RequestBuilder builder = RequestBuilder.post("/v3/detect_faces");
    builder.query(VERSION, versionDate);
    MultipartBody.Builder multipartBuilder = new MultipartBody.Builder();
    multipartBuilder.setType(MultipartBody.FORM);
    if (detectFacesOptions.imagesFile() != null) {
      RequestBody imagesFileBody = RequestUtils.inputStreamBody(detectFacesOptions.imagesFile(), detectFacesOptions
          .imagesFileContentType());
      multipartBuilder.addFormDataPart("images_file", detectFacesOptions.imagesFilename(), imagesFileBody);
    }
    if (detectFacesOptions.url() != null) {
      multipartBuilder.addFormDataPart("url", detectFacesOptions.url());
    }
    builder.body(multipartBuilder.build());
    return createServiceCall(builder.build(), ResponseConverterUtils.getObject(DetectedFaces.class));
  }