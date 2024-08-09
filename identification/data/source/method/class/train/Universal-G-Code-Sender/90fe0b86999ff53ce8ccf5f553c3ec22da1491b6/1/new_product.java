public static String generateMoveToCommand(String command, PartialPosition position, double feedRate) {
        StringBuilder sb = new StringBuilder();

        sb.append(unitCommand(position.getUnits()));
        sb.append(command);

        // Add all axises
        sb.append(position.getFormattedGCode());

        String convertedFeedRate = Utils.formatter.format(feedRate);
        if (feedRate > 0 && convertedFeedRate != null) {
            sb.append("F").append(convertedFeedRate);
        }

        return sb.toString();
    }