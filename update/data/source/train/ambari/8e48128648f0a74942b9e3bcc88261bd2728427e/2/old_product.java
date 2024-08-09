public Set<AlertDefinition> getAlertDefinitions(String stackName, String stackVersion,
      String serviceName) throws AmbariException {
    
    ServiceInfo svc = getService(stackName, stackVersion, serviceName);

    if (null == svc.getAlertsFile() || !svc.getAlertsFile().exists()) {
      LOG.debug("Alerts file for " + stackName + "/" + stackVersion + "/" + serviceName + " not found.");
      return null;
    }
    
    Map<String, List<AlertDefinition>> map = null;

    GsonBuilder builder = new GsonBuilder().registerTypeAdapter(Source.class,
        new JsonDeserializer<Source>() {
          @Override
          public Source deserialize(JsonElement json, Type typeOfT,
              JsonDeserializationContext context) throws JsonParseException {
            JsonObject jsonObj = (JsonObject) json;

            SourceType type = SourceType.valueOf(jsonObj.get("type").getAsString());
            Class<? extends Source> cls = null;
            
            switch (type) {
              case METRIC:
                cls = MetricSource.class;
                break;
              default:
                break;
            }

            if (null != cls)
              return context.deserialize(json, cls);
            else
              return null;
          }
        });
    
    Gson gson = builder.create();

    try {
      Type type = new TypeToken<Map<String, List<AlertDefinition>>>(){}.getType();
      map = gson.fromJson(new FileReader(svc.getAlertsFile()), type);
    } catch (Exception e) {
      LOG.error ("Could not read the alert definition file", e);
      throw new AmbariException("Could not read alert definition file", e);
    }

    Set<AlertDefinition> defs = new HashSet<AlertDefinition>();
    
    for (Entry<String, List<AlertDefinition>> entry : map.entrySet()) {
      for (AlertDefinition ad : entry.getValue()) {
        ad.setServiceName(serviceName);
        if (!entry.getKey().equals("service")) {
          ad.setComponentName(entry.getKey());
        }
      }
      defs.addAll(entry.getValue());
    }
    
    return defs;
  }