static long toReceivedTimeMillis(final String value) {
            if (value == null) {
                return 0;
            }

            final int length = value.length();
            // e.g. 1504230492.763
            final int millisPosition = value.lastIndexOf('.');
            if (millisPosition != -1) {
                if ((length - millisPosition) != 4) {
                    // invalid format.
                    return 0;
                }
                try {
                    return Long.parseLong(value.substring(0, millisPosition) + value.substring(millisPosition + 1));
                } catch (NumberFormatException ignored) {
                }
            }
            return 0;
        }