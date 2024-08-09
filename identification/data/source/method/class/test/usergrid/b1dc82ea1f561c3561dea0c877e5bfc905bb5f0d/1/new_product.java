public int getNextRunNumber( List<Runner> runners, String commitId ) {
        if( runners.size() == 0 ) {
            return 1;
        }

        int maxRunNumber = 0;
        for( Runner runner: runners ) {
            List<Run> runs = getRuns( runner.getHostname(), commitId );
            for( Run run: runs ) {
                if( run.getRunNumber() > maxRunNumber ) {
                    maxRunNumber = run.getRunNumber();
                }
            }
        }

        return maxRunNumber + 1;
    }