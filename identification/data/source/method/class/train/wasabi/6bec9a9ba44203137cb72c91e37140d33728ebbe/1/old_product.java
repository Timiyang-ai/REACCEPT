@Override
    public List<Map> doPageAssignments(Application.Name applicationName, Page.Name pageName, User.ID userID,
                                       Context context, boolean createAssignment, boolean ignoreSamplingPercent,
                                       HttpHeaders headers, SegmentationProfile segmentationProfile) {

        //Get the experiments (id & allowNewAssignment only) associated to the given application and page.
        List<PageExperiment> pageExperimentList = getExperiments(applicationName, pageName);

        //Prepare allowAssignments map
        Map<Experiment.ID, Boolean> allowAssignments = new HashMap<>(pageExperimentList.size());
        for (PageExperiment pageExperiment : pageExperimentList) {
            allowAssignments.put(pageExperiment.getId(), pageExperiment.getAllowNewAssignment());
        }

        //Prepare experiment batch
        ExperimentBatch.Builder experimentBatchBuilder = ExperimentBatch.newInstance();
        if (segmentationProfile != null) {
            experimentBatchBuilder.withProfile(segmentationProfile.getProfile());
        }
        ExperimentBatch experimentBatch = experimentBatchBuilder.build();

        return doBatchAssignments(userID, applicationName, context,
                createAssignment, ignoreSamplingPercent, headers, experimentBatch, pageName, allowAssignments);

    }