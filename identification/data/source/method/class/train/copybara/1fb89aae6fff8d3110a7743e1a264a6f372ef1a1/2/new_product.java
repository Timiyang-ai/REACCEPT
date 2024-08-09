ImmutableList<DestinationEffect> process(
        String output,
        boolean newPush,
        GitRepository alternateRepo,
        List<? extends Change<?>> current);