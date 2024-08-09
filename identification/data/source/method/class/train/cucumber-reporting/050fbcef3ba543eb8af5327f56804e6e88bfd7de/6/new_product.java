public Reportable generateReports() {
        Trends trends = null;

        try {
            // first copy static resources so ErrorPage is displayed properly
            copyStaticResources();

            // create directory for embeddings before files are generated
            createEmbeddingsDirectory();

            // parse json files for results
            List<Feature> features = reportParser.parseJsonFiles(jsonFiles);
            reportResult = new ReportResult(features, configuration.getSortingMethod());
            Reportable reportable = reportResult.getFeatureReport();

            if (configuration.isTrendsStatsFile()) {
                // prepare data required by generators, collect generators and generate pages
                trends = updateAndSaveTrends(reportable);
            }

            List<AbstractPage> pages = collectPages(trends);
            generatePages(pages);

            return reportable;

            // whatever happens we want to provide at least error page instead of incomplete report or exception
        } catch (Exception e) {
            generateErrorPage(e);
            // update trends so there is information in history that the build failed

            // if trends was not created then something went wrong
            // and information about build failure should be saved
            if (!wasTrendsFileSaved && configuration.isTrendsStatsFile()) {
                Reportable reportable = new EmptyReportable();
                updateAndSaveTrends(reportable);
            }

            // something went wrong, don't pass result that might be incomplete
            return null;
        }
    }