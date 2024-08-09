public void dump ()
    {
        System.out.println();

        if (name != null) {
            System.out.print(name);
        }

        System.out.println(" Check Suite: threshold=" + threshold);

        dumpSpecific();

        System.out.println(
                "Weight    Name             Covariant    Low       High");
        System.out.println(
                "------    ----                ------    ---       ----");

        int index = 0;

        for (Check<C> check : checks) {
            System.out.printf(
                    "%4.1f      %-19s  %5b  % 6.2f    % 6.2f %n",
                    weights.get(index++),
                    check.getName(),
                    check.isCovariant(),
                    check.getLow(),
                    check.getHigh());
        }
    }