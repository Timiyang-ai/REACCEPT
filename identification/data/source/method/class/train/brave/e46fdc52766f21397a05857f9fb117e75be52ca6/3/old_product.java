@Deprecated public Builder addRule(@Nullable String method, String path, float probability) {
      return putRuleWithProbability(method, path, probability);
    }