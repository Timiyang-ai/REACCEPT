public void dump ()
    {
        StringBuilder sb = new StringBuilder(String.format("%n"));

        if (name != null) {
            sb.append(name);
        }

        sb.append(String.format(" Check Suite: threshold=%d%n", threshold));

        dumpSpecific(sb);

        sb.append(String.format(
                "Weight    Name             Covariant    Low       High%n"));
        sb.append(String.format(
                "------    ----                ------    ---       ----%n"));

        int index = 0;

        for (Check<C> check : checks) {
            sb.append(String.format(
                    "%4.1f      %-19s  %5b  % 6.2f    % 6.2f %n",
                    weights.get(index++),
                    check.getName(),
                    check.isCovariant(),
                    check.getLow(),
                    check.getHigh()));
        }
        
        logger.info(sb.toString());
    }