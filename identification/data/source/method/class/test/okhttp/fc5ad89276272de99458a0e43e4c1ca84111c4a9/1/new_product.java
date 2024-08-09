public Builder addEncodedPathSegments(String encodedPathSegments) {
      if (encodedPathSegments == null) {
        throw new NullPointerException("encodedPathSegments == null");
      }
      return addPathSegments(encodedPathSegments, true);
    }