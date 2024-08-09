@Override
    public ExperimentCumulativeCounts getExperimentCountsDailies(final Experiment.ID experimentId, final Parameters parameters) {

        Experiment exp = getExperimentIfExists(experimentId);

        //set start and end timestamps and use to create calendars
        Date start_ts = parameters.getFromTime();

        if (Objects.isNull(start_ts)) {
            start_ts = exp.getStartTime();
        }

        Calendar start_cal = createCalendarMidnight(start_ts);
        Date end_ts = parameters.getToTime();

        if (Objects.isNull(end_ts)) {
            end_ts = exp.getEndTime();
        }

        Calendar end_cal = createCalendarMidnight(end_ts);
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        df.setTimeZone(TimeZone.getTimeZone("UTC"));

        List<DailyCounts> days = new ArrayList<>();

        //loop over days using calendars
        for (; start_cal.compareTo(end_cal) <= 0; start_cal.add(Calendar.DATE, 1)) {
            Calendar to_cal = (Calendar) start_cal.clone();

            to_cal.add(Calendar.DATE, 1);
            to_cal.add(Calendar.MILLISECOND, -1);

            String currentDate = df.format(start_cal.getTime());

            //fetch the counts for the current day only
            Parameters dailyParams = parameters.clone();
            dailyParams.setFromTime(new Date(start_cal.getTime().getTime()));
            dailyParams.setToTime(new Date(to_cal.getTime().getTime()));

            ExperimentCounts perDayCount = getExperimentCounts(experimentId, dailyParams);

            //fetch the counts from the beginning of the experiment up through the current day
            Parameters cumulativeParams = parameters.clone();
            cumulativeParams.setFromTime(null);
            cumulativeParams.setToTime(new Date(to_cal.getTime().getTime()));

            ExperimentCounts cumulativeCount = getExperimentCounts(experimentId, cumulativeParams);

            days.add(new DailyCounts.Builder().setDate(currentDate).withPerDay(perDayCount)
                    .withCumulative(cumulativeCount).build());

        }

        return new ExperimentCumulativeCounts.Builder().withDays(days).build();
    }