private void SetViewInstanceProperties(ViewInstanceEntity instanceEntity, Map<String, String> properties, ViewConfig viewConfig, ClassLoader classLoader) throws SystemException {
    try {
      Masker masker = getMasker(viewConfig.getMaskerClass(classLoader));

      Map<String, ParameterConfig> parameterConfigMap = new HashMap<String, ParameterConfig>();
      for (ParameterConfig paramConfig : viewConfig.getParameters()) {
        parameterConfigMap.put(paramConfig.getName(), paramConfig);
      }
      for (Map.Entry<String, String> entry : properties.entrySet()) {
        String name  = entry.getKey();
        String value = entry.getValue();

        ParameterConfig parameterConfig = parameterConfigMap.get(name);

        if (parameterConfig != null && parameterConfig.isMasked()) {
          value = masker.mask(value);
        }
        instanceEntity.putProperty(name, value);
      }
    } catch (Exception e) {
      throw new SystemException("Caught exception while setting instance property.", e);
    }
  }