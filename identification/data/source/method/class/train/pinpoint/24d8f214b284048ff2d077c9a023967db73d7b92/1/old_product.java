long toReceivedTimeMillis(final String value) {
        if (value == null) {
            return 0;
        }

        final int length = value.length();
        final int millisPosition = value.lastIndexOf('.');
        if (millisPosition != -1) {
            // e.g. 1504230492.763
            if ((length - millisPosition) != 4) {
                // invalid format.
                return 0;
            }
            try {
                return Long.parseLong(value.substring(0, millisPosition) + value.substring(millisPosition + 1));
            } catch (NumberFormatException ignored) {
            }
        } else {
            // convert to milliseconds from microseconds.
            if (length > 3) {
                try {
                    return Long.parseLong(value.substring(0, length - 3));
                } catch (NumberFormatException ignored) {
                }
            }
        }
        return 0;
    }