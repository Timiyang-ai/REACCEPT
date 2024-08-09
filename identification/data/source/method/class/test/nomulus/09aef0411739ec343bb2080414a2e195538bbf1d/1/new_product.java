public void export(ImmutableList<Class> entityClasses, File outputFile) {
    // Configure Hibernate settings.
    Map<String, String> settings = Maps.newHashMap();
    settings.put(Environment.DIALECT, NomulusPostgreSQLDialect.class.getName());
    settings.put(Environment.URL, jdbcUrl);
    settings.put(Environment.USER, username);
    settings.put(Environment.PASS, password);
    settings.put(Environment.HBM2DDL_AUTO, "none");
    settings.put(Environment.SHOW_SQL, "true");
    settings.put(
        Environment.PHYSICAL_NAMING_STRATEGY, NomulusNamingStrategy.class.getCanonicalName());

    try (StandardServiceRegistry registry =
        new StandardServiceRegistryBuilder().applySettings(settings).build()) {
      MetadataSources metadata = new MetadataSources(registry);

      // Note that we need to also add all converters to the Hibernate context because
      // the entity class may use the customized type.
      Stream.concat(entityClasses.stream(), findAllConverters().stream())
          .forEach(metadata::addAnnotatedClass);

      SchemaExport export = new SchemaExport();
      export.setHaltOnError(true);
      export.setFormat(true);
      export.setDelimiter(";");
      export.setOutputFile(outputFile.getAbsolutePath());
      export.createOnly(EnumSet.of(TargetType.SCRIPT), metadata.buildMetadata());
    }
  }