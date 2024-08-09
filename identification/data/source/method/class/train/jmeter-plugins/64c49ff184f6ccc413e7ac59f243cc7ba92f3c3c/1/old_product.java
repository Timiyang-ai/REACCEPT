public Color getNextColor() {
        Color ret;
        index++;
        if (index < fixedColors.length) {
            ret = fixedColors[index];
        } else {
            int rndIndex = index - fixedColors.length;

            if (randomColors.size() > rndIndex) {
                ret = randomColors.get(rndIndex);
            } else {
                ret = new Color(rnd.nextInt(0xFFFFFF));
                randomColors.add(ret);
            }
        }
        return ret;
    }