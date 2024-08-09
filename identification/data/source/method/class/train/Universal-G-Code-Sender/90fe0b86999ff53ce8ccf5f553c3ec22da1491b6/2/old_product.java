public static String generateMoveToCommand(Position position, double feedRate) {
        StringBuilder sb = new StringBuilder();

        sb.append(unitCommand(position.getUnits()));
        sb.append(Code.G90.name());
        sb.append(Code.G1.name());

        // Add all axises
        String positionCommand = Arrays.stream(Axis.values())
                .map(axis -> axis.name() + Utils.formatter.format(position.get(axis)))
                .collect(Collectors.joining());
        sb.append(positionCommand);

        String convertedFeedRate = Utils.formatter.format(feedRate);
        if (feedRate > 0 && convertedFeedRate != null) {
            sb.append("F").append(convertedFeedRate);
        }

        return sb.toString();
    }