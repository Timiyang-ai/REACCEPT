public Color getNextColor() {
        Color color = null;

        doCycles();

        int r = 0, g = 0, b = 0;
        if ((bits & 1) == 1) {
            r = level;
        }
        if ((bits & 2) == 2) {
            g = level;
        }
        if ((bits & 4) == 4) {
            b = level;
        }
        Color c = new Color(r, g, b);
        if (assignedColors.contains(c)) {
            System.out.println("Existing " + r + " " + g + " " + b);
            color = getNextColor();
        } else if ((r + g + b) / 3 < 32) {
            log.debug("Too dark " + r + " " + g + " " + b);
            color = getNextColor();
        } else if ((r + g + b) / 3 > 256 - 64) {
            log.debug("Too light " + r + " " + g + " " + b);
            color = getNextColor();
        } else {
            log.debug("New " + r + " " + g + " " + b);
            color = new Color(r, g, b);
        }

        assignedColors.add(color);
        return color;
    }