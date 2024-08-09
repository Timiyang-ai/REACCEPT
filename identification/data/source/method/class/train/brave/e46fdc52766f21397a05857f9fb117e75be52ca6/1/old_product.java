public Builder addRule(@Nullable String method, String path, float rate) {
      rules.add(new MethodAndPathRule(method, path, rate));
      return this;
    }