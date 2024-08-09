public static String generateMoveToCommand(PartialPosition position, double feedRate) {
        StringBuilder sb = new StringBuilder();

        sb.append(unitCommand(position.getUnits()));
        sb.append(Code.G90.name());
        sb.append(Code.G1.name());

        // Add all axises
        sb.append(position.getFormattedGCode());

        String convertedFeedRate = Utils.formatter.format(feedRate);
        if (feedRate > 0 && convertedFeedRate != null) {
            sb.append("F").append(convertedFeedRate);
        }

        return sb.toString();
    }