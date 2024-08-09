public static SlotKey parse(String string) {
        String[] tokens = string.split(",");
        if (tokens.length != 3) {
            return null;
        }
        Granularity granularity = Granularity.fromString(tokens[0]);
        if (granularity == null) {
            return null;
        }
        try {
            int slot = Integer.parseInt(tokens[1]);
            int shard = Integer.parseInt(tokens[2]);
            return of(granularity, slot, shard);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }