@Override public String route(ContainerResponse response) {
      return (String) response.getRequestContext().getProperty("http.route");
    }