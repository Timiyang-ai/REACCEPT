public static IntentId valueOf(String value) {
        long id = value.toLowerCase().startsWith("0x")
                ? Long.parseLong(value.substring(2), HEX)
                : Long.parseLong(value, DEC);
        return new IntentId(id);
    }