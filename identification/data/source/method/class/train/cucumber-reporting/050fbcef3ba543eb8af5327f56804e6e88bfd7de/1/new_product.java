public Reportable generateReports() {
        try {
            // first copy static resources so ErrorPage is displayed properly
            copyStaticResources();

            // create directory for embeddings before files are generated
            createEmbeddingsDirectory();

            List<Feature> features = reportParser.parseJsonFiles(jsonFiles);
            reportResult = new ReportResult(features);

            List<AbstractPage> pages = collectPages();
            generatePages(pages);

            return reportResult.getFeatureReport();

            // whatever happens we want to provide at least error page instead of empty report
        } catch (Exception e) {
            generateErrorPage(e);
            // something went wrong, don't pass result that might be incomplete
            return null;
        }
    }