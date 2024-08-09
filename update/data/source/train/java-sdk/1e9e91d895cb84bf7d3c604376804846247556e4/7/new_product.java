public String getValue() {
    return GsonSerializationHelper.serializeDynamicModelProperty(this.get("value"), valueType);
  }