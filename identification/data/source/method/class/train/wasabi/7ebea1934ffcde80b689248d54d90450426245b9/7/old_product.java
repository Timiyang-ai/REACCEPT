@Override
    public ExperimentCounts getExperimentCounts(final Experiment.ID experimentID, final Parameters parameters) {


        return (ExperimentCounts) transactionFactory.transaction(new Block() {
            @Override
            public Object value(Transaction transaction) {

                assertExperimentExists(experimentID);

                List<Map> impressionRows = analyticsRepository.getImpressionRows(experimentID, parameters);
                List<Map> actionsRows = analyticsRepository.getActionsRows(experimentID, parameters);
                List<Map> jointActionsRows = analyticsRepository.getJointActions(experimentID, parameters);
                //fetch list of buckets for experiment and use to create counts objects
                Map<Bucket.Label, BucketCounts> buckets = analyticsRepository.getEmptyBuckets(experimentID);

                //loop over each of the SQL results to fill BucketCounts objects with counts
                for (Map actionRow : actionsRows) {
                    BucketCounts bucket = buckets.get(Bucket.Label.valueOf((String) actionRow.get("bid")));
                    Event.Name actionName = Event.Name.valueOf((String) actionRow.get(ACTION));
                    bucket.addActionCounts(actionName, new ActionCounts.Builder()
                            .withActionName(actionName)
                            .withEventCount((Long) actionRow.get("c"))
                            .withUniqueUserCount((Long) actionRow.get("cu"))
                            .build());
                }

                for (Map impressionRow : impressionRows) {
                    BucketCounts bucket = buckets.get(Bucket.Label.valueOf((String) impressionRow.get("bid")));

                    bucket.setImpressionCounts(new Counts.Builder()
                            .withEventCount((Long) impressionRow.get("c"))
                            .withUniqueUserCount((Long) impressionRow.get("cu"))
                            .build());
                }

                for (Map jointActionsRow : jointActionsRows) {
                    BucketCounts bucket = buckets.get(Bucket.Label.valueOf((String) jointActionsRow.get("bid")));

                    bucket.setJointActionCounts(new Counts.Builder()
                            .withEventCount((Long) jointActionsRow.get("c"))
                            .withUniqueUserCount((Long) jointActionsRow.get("cu"))
                            .build());
                }

                return analysisTools.calculateExperimentCounts(buckets.values());
            }
        });
    }