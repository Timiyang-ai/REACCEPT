@Override
    public boolean checkMostRecentRollup(Experiment experiment, Parameters parameters, Date to)
            throws RepositoryException {

        try {
            Timestamp toTime = new Timestamp(to.getTime());


            final String SQL_SELECT_ID = "SELECT day FROM experiment_rollup " +
                    "WHERE experiment_id=? AND context=? ORDER BY day";

            List result = transaction.select(SQL_SELECT_ID,
                    experiment.getID(),
                    parameters.getContext().getContext());

            if (result.isEmpty()) {
                return true;
            } else {
                Map row = (Map) result.get(0);
                Date maxDay = (Date) row.get("day");
                Timestamp maxStamp = new Timestamp(maxDay.getTime());
                return maxStamp.after(toTime);
            }
        } catch (Exception e) {
            throw new RepositoryException("error reading counts from MySQL rollups", e);
        }
    }