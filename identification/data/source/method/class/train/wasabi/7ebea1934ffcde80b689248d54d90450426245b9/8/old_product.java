@Override
    public ExperimentCounts getExperimentRollup(final Experiment.ID experimentID, final Parameters parameters) {

        return (ExperimentCounts) transactionFactory.transaction(new Block() {
            @Override
            public Object value(Transaction transaction) {

                //if from_ or to_ time or actions specified, calculate counts from actions and impressions tables directly
                if (circumventRollup(experimentID, parameters)) {
                    return getExperimentCounts(experimentID, parameters);
                }

                //check that experiment exists and fetch metadata
                Experiment exp = getExperimentIfExists(experimentID);

                Rollup rollup = new Rollup(exp, true, transaction);
                if (!rollup.isFreshEnough()) {
                    return getExperimentCounts(experimentID, parameters);
                }

                //get rolluprows from the database
                List<Map> rollupRows = analyticsRepository.getRollupRows(experimentID,
                        rollup.latestAvailableRollupDateAsString(),
                        parameters);

                //fetch list of buckets for experiment and use to create counts objects
                Map<Bucket.Label, BucketCounts> buckets = analyticsRepository.getEmptyBuckets(experimentID);

                //loop over rollup rows to fill BucketCounts objects with counts
                for (Map rollupRow : rollupRows) {
                    // fixme: ref rollup domain object
                    BucketCounts bucket = buckets.get(Bucket.Label.valueOf((String) rollupRow.get("bid")));

                    // fixme: ref rollup domain object
                    if ("".equals(rollupRow.get(ACTION))) {
                        bucket.setImpressionCounts(new Counts.Builder()
                                .withEventCount(Long.valueOf((Integer) rollupRow.get("ic")))
                                .withUniqueUserCount(Long.valueOf((Integer) rollupRow.get("iuc")))
                                .build());
                        bucket.setJointActionCounts(new Counts.Builder()
                                .withEventCount(Long.valueOf((Integer) rollupRow.get("ac")))
                                .withUniqueUserCount(Long.valueOf((Integer) rollupRow.get("auc")))
                                .build());
                    } else {
                        Event.Name actionName = Event.Name.valueOf((String) rollupRow.get(ACTION));
                        bucket.addActionCounts(actionName, new ActionCounts.Builder()
                                .withActionName(actionName)
                                .withEventCount(Long.valueOf((Integer) rollupRow.get("ac")))
                                .withUniqueUserCount(Long.valueOf((Integer) rollupRow.get("auc")))
                                .build());
                    }
                }

                return analysisTools.calculateExperimentCounts(buckets.values());
            }
        });
    }