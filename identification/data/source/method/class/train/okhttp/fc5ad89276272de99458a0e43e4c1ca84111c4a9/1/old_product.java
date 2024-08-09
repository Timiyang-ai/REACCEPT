public Builder addPathSegments(String pathSegments) {
      if (pathSegments == null) throw new IllegalArgumentException("pathSegments == null");
      return addPathSegments(pathSegments, false);
    }