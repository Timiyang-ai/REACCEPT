  @Test public void annotationQuery_roundTrip() {
    String annotationQuery = "http.method=GET and error";

    QueryRequest request = queryBuilder
      .serviceName("security-service")
      .parseAnnotationQuery(annotationQuery)
      .build();

    assertThat(request.annotationQuery())
      .containsEntry("error", "")
      .containsEntry("http.method", "GET");

    assertThat(request.annotationQueryString())
      .isEqualTo(annotationQuery);
  }