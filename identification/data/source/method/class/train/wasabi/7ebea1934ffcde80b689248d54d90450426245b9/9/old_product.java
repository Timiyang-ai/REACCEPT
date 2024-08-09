@Override
    public ExperimentCumulativeCounts getExperimentRollupDailies(final Experiment.ID experimentID,
                                                                 final Parameters parameters) {
        return (ExperimentCumulativeCounts) transactionFactory.transaction(new Block() {
            @Override
            public Object value(Transaction transaction) {

                //if from_ or to_ time or actions specified, calculate counts from actions and impressions tables directly
                if (circumventRollup(experimentID, parameters)) {
                    return getExperimentCountsDailies(experimentID, parameters);
                }

                Experiment exp = getExperimentIfExists(experimentID);

                Rollup rollup = new Rollup(exp, transaction);
                if (!rollup.isFreshEnough())
                    return getExperimentCountsDailies(experimentID, parameters);

                List<Map> rollupRows = analyticsRepository.getCountsFromRollups(experimentID, parameters);

                // fetch list of buckets for experiment and use to pre-populate buckets for perDay and cumulative
                Map<Bucket.Label, BucketCounts> buckets = analyticsRepository.getEmptyBuckets(experimentID);
                ExperimentCounts experiment = analysisTools.calculateExperimentCounts(buckets.values());

                //create calendars to hold the start and end day
                Date start_ts = exp.getStartTime();
                Calendar start_cal = createCalendarMidnight(start_ts);
                Date end_ts = exp.getEndTime();
                Calendar end_cal = createCalendarMidnight(end_ts);
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

                df.setTimeZone(TimeZone.getTimeZone("UTC"));

                //loop over days using calendars to create DailyCounts with zero counts for each day
                List<DailyCounts> days = new ArrayList<>();

                for (; start_cal.compareTo(end_cal) <= 0; start_cal.add(Calendar.DATE, 1)) {
                    String thisDate = df.format(start_cal.getTime());

                    days.add(new DailyCounts.Builder().setDate(thisDate).withPerDay(experiment.clone())
                            .withCumulative(experiment.clone()).build());
                }

                int numberDays = days.size();
                int currentDay = 0;
                //loop over rollup rows to update DailyCounts for each day
                Map<Bucket.Label, BucketCounts> perDayBuckets = new HashMap<>();
                Map<Bucket.Label, BucketCounts> cumulativeBuckets = new HashMap<>();

                for (Map.Entry<Bucket.Label, BucketCounts> bucketEntry : buckets.entrySet()) {
                    perDayBuckets.put(bucketEntry.getKey(), bucketEntry.getValue().clone());
                    cumulativeBuckets.put(bucketEntry.getKey(), bucketEntry.getValue().clone());
                }

                int numberRows = rollupRows.size();

                for (int n = 0; n < numberRows; n++) {
                    Map rollupRow = rollupRows.get(n);
                    Bucket.Label bucketLabel = Bucket.Label.valueOf((String) rollupRow.get("bid"));

                    if ("".equals(rollupRow.get(ACTION))) {
                        Counts bucketImpressions = new Counts.Builder()
                                .withEventCount(Long.valueOf((Integer) rollupRow.get("ic")))
                                .withUniqueUserCount(Long.valueOf((Integer) rollupRow.get("iuc")))
                                .build();
                        Counts bucketJointActions = new Counts.Builder()
                                .withEventCount(Long.valueOf((Integer) rollupRow.get("ac")))
                                .withUniqueUserCount(Long.valueOf((Integer) rollupRow.get("auc")))
                                .build();

                        if ((Boolean) rollupRow.get("c")) {
                            cumulativeBuckets.get(bucketLabel).setImpressionCounts(bucketImpressions);
                            cumulativeBuckets.get(bucketLabel).setJointActionCounts(bucketJointActions);
                        } else {
                            perDayBuckets.get(bucketLabel).setImpressionCounts(bucketImpressions);
                            perDayBuckets.get(bucketLabel).setJointActionCounts(bucketJointActions);
                        }
                    } else {
                        Event.Name actionName = Event.Name.valueOf((String) rollupRow.get(ACTION));
                        ActionCounts bucketAction = new ActionCounts.Builder()
                                .withActionName(actionName)
                                .withEventCount(Long.valueOf((Integer) rollupRow.get("ac")))
                                .withUniqueUserCount(Long.valueOf((Integer) rollupRow.get("auc")))
                                .build();

                        if ((Boolean) rollupRow.get("c")) {
                            cumulativeBuckets.get(bucketLabel).addActionCounts(actionName, bucketAction);
                        } else {
                            perDayBuckets.get(bucketLabel).addActionCounts(actionName, bucketAction);
                        }
                    }

                    //update the DailyCounts if this is the last row for this date
                    Date thisDay = (Date) rollupRow.get("day");

                    if ((n == numberRows - 1) || (!rollupRows.get(n + 1).get("day").equals(thisDay))) {
                        //calculate date string and create a new DailyCounts for this day
                        String thisDate = df.format(thisDay);

                        while (currentDay < numberDays) {
                            if (days.get(currentDay).getDate().equals(thisDate)) {
                                days.set(currentDay, new DailyCounts.Builder().setDate(thisDate)
                                        .withPerDay(analysisTools.calculateExperimentCounts(perDayBuckets.values()))
                                        .withCumulative(analysisTools.calculateExperimentCounts(cumulativeBuckets.values()))
                                        .build());

                                currentDay += 1;

                                break;
                            } else {
                                //carry over cumulative counts from previous day if there are no new counts
                                if (currentDay > 0) {
                                    DailyCounts currentDailyCounts = days.get(currentDay);
                                    DailyCounts missingDailyCounts = getPreviousDayDailyCountAsCurrentDailyCount(
                                            currentDailyCounts, days, currentDay);
                                    days.set(currentDay, missingDailyCounts);
                                }

                                currentDay += 1;
                            }
                        }

                        //reset the perDay and cumulative maps for the next day
                        perDayBuckets = new HashMap<>();
                        cumulativeBuckets = new HashMap<>();

                        for (Map.Entry<Bucket.Label, BucketCounts> bucketEntry : buckets.entrySet()) {
                            perDayBuckets.put(bucketEntry.getKey(), bucketEntry.getValue().clone());
                            cumulativeBuckets.put(bucketEntry.getKey(), bucketEntry.getValue().clone());
                        }
                    }
                }

                //finish filling the cumulative counts if not already done
                for (; currentDay < numberDays; currentDay += 1) {
                    DailyCounts thisDailyCounts = days.get(currentDay);
                    DailyCounts currentDailyCount = getPreviousDayDailyCountAsCurrentDailyCount(
                            thisDailyCounts, days, currentDay);

                    days.set(currentDay, currentDailyCount);
                }

                return new ExperimentCumulativeCounts.Builder().withDays(days).build();
            }
        });
    }