@Override
    public List<Map> getImpressionRows(Experiment.ID experimentID, Parameters parameters)
            throws RepositoryException {

        try {

            //build and execute SQL queries for counts
            Date from_ts = parameters.getFromTime();
            Date to_ts = parameters.getToTime();
            String sqlBase = "bucket_label as bid, count(user_id) as c, count(distinct user_id) as cu";
            String sqlParams = " where experiment_id = ? and context = ?";
            List params = new ArrayList();
            params.add(experimentID);
            params.add(parameters.getContext().getContext());


            if (from_ts != null) {
                params.add(from_ts);
                sqlParams += " and timestamp >= ?";
            }

            if (to_ts != null) {
                params.add(to_ts);
                sqlParams += " and timestamp <= ?";
            }

            Object[] bucketSqlData = new Object[params.size()];
            params.toArray(bucketSqlData);

            String sqlImpressions = "select " + sqlBase + " from event_impression" +
                    sqlParams + " group by bucket_label";
            List<Map> impressionRows = transaction.select(sqlImpressions, bucketSqlData);

            return impressionRows;
        } catch (Exception e) {
            throw new RepositoryException("error reading actions rows from MySQL", e);
        }
    }