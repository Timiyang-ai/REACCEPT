public int getNextRunNumber( String commitId ) {
        Collection<Run> runs = getRuns( commitId );
        int maxRunNumber = 0;
        for( Run run: runs ) {
            if( run.getRunNumber() > maxRunNumber ) {
                maxRunNumber = run.getRunNumber();
            }
        }

        return maxRunNumber + 1;
    }