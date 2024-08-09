@POST
  @Consumes(MediaType.MULTIPART_FORM_DATA)
  @Produces(MediaType.TEXT_HTML)
  public Response doPostImport(@FormDataParam("importDir") String uploadDir,
                               @FormDataParam("fileUpload") InputStream fileIS, @FormDataParam("fileUpload") FormDataContentDisposition fileInfo) {
    Map<String, Converter> converters = new HashMap<String, Converter>();
    StreamConverter streamConverter = new StreamConverter();
    converters.put("prpt", streamConverter); //$NON-NLS-1$
    converters.put("mondrian.xml", streamConverter); //$NON-NLS-1$
    converters.put("kjb", streamConverter); //$NON-NLS-1$
    converters.put("ktr", streamConverter); //$NON-NLS-1$
    converters.put("report", streamConverter); //$NON-NLS-1$
    converters.put("rptdesign", streamConverter); //$NON-NLS-1$
    converters.put("svg", streamConverter); //$NON-NLS-1$
    converters.put("url", streamConverter); //$NON-NLS-1$
    converters.put("xaction", streamConverter); //$NON-NLS-1$
    converters.put("xanalyzer", streamConverter); //$NON-NLS-1$
    converters.put("xcdf", streamConverter); //$NON-NLS-1$
    converters.put("xdash", streamConverter); //$NON-NLS-1$
    converters.put("xreportspec", streamConverter); //$NON-NLS-1$
    converters.put("waqr.xaction", streamConverter); //$NON-NLS-1$
    converters.put("xwaqr", streamConverter);//$NON-NLS-1$
    converters.put("gif", streamConverter); //$NON-NLS-1$
    converters.put("css", streamConverter); //$NON-NLS-1$
    converters.put("html", streamConverter); //$NON-NLS-1$
    converters.put("htm", streamConverter); //$NON-NLS-1$
    converters.put("jpg", streamConverter); //$NON-NLS-1$
    converters.put("jpeg", streamConverter); //$NON-NLS-1$
    converters.put("js", streamConverter); //$NON-NLS-1$
    converters.put("cfg.xml", streamConverter); //$NON-NLS-1$
    converters.put("jrxml", streamConverter); //$NON-NLS-1$
    converters.put("png", streamConverter); //$NON-NLS-1$
    converters.put("properties", streamConverter); //$NON-NLS-1$
    converters.put("sql", streamConverter); //$NON-NLS-1$
    converters.put("xmi", streamConverter); //$NON-NLS-1$
    converters.put("xml", streamConverter); //$NON-NLS-1$
    converters.put("cda", streamConverter); //$NON-NLS-1$

    FileImporter fileImporter = new FileImporter(repository, uploadDir, converters);
//    addContentHandlers(fileImporter, repository);

    try {
      if (fileInfo.getFileName().toLowerCase().endsWith(".zip")) { //$NON-NLS-1$
        ImportSource src = new ZipSolutionRepositoryImportSource(new ZipInputStream(fileIS), "UTF-8", new String[]{RepositoryFile.SEPARATOR + "system" + RepositoryFile.SEPARATOR, ".mondrian.xml", "datasources.xml"}); //$NON-NLS-1$
        fileImporter.doImport(src, null, true);
        for (ImportSource dependentImportSource : src.getDependentImportSources()) {
          dependentImportSource.initialize(repository);
          dependentImportSource.setOwnerName(uploadDir);
          FileImporter rootFileImporter = new FileImporter(repository, RepositoryFile.SEPARATOR, converters);
          rootFileImporter.doImport(dependentImportSource, null, true);
        }
      } else {
        ImportSource src = new SingleFileStreamImportSource(fileIS, fileInfo.getFileName(), "UTF-8"); //$NON-NLS-1$
        fileImporter.doImport(src, null, true);
      }

    } catch (IOException e) {
      return Response.serverError().entity(e.toString()).build();
//    } catch (InitializationException e) {
//      e.printStackTrace();
    }

    return Response.ok(Messages.getInstance().getString("FileResource.IMPORT_SUCCESS")).build(); //$NON-NLS-1$
  }