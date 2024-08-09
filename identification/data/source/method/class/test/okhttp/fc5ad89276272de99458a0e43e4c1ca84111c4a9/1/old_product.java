public Builder addEncodedPathSegments(String encodedPathSegments) {
      if (encodedPathSegments == null) {
        throw new IllegalArgumentException("encodedPathSegments == null");
      }
      return addPathSegments(encodedPathSegments, true);
    }