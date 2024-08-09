public static String generateMoveCommand(String command, double distance, double feedRate, int dirX, int dirY, int dirZ) {
        StringBuilder sb = new StringBuilder();

        // Scale the feed rate and distance to the current coordinate units
        String convertedDistance = Utils.formatter.format(distance);
        String convertedFeedRate = Utils.formatter.format(feedRate);

        // Set command.
        sb.append(command);

        if (dirX != 0) {
            sb.append("X");
            if (dirX < 0) {
                sb.append("-");
            }
            sb.append(convertedDistance);
        }

        if (dirY != 0) {
            sb.append("Y");
            if (dirY < 0) {
                sb.append("-");
            }
            sb.append(convertedDistance);
        }

        if (dirZ != 0) {
            sb.append("Z");
            if (dirZ < 0) {
                sb.append("-");
            }
            sb.append(convertedDistance);
        }

        if (convertedFeedRate != null) {
            sb.append("F").append(convertedFeedRate);
        }

        return sb.toString();
    }