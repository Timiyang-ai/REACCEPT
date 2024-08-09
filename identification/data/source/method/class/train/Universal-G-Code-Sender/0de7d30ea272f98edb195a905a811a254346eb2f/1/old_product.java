public static String generateXYZ(String command, Units units,
            String distance, String feedRate, int dirX, int dirY, int dirZ) {
        StringBuilder sb = new StringBuilder();

      // Add units.
        sb.append(unitCommand(units));

        // Set command.
        sb.append(command);

        if (dirX != 0) {
            sb.append("X");
            if (dirX < 0) {
                sb.append("-");
            }
            sb.append(distance);
        }

        if (dirY != 0) {
            sb.append("Y");
            if (dirY < 0) {
                sb.append("-");
            }
            sb.append(distance);
        }

        if (dirZ != 0) {
            sb.append("Z");
            if (dirZ < 0) {
                sb.append("-");
            }
            sb.append(distance);
        }

        if (feedRate != null) {
            sb.append("F").append(feedRate);
        }

        return sb.toString();
    }