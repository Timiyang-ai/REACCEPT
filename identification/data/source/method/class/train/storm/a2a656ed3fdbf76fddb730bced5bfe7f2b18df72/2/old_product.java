public static Double parseJvmHeapMemByChildOpts(String input, Double defaultValue) {
        if (input != null) {
            Pattern optsPattern = Pattern.compile("Xmx[0-9]+[mkgMKG]");
            Matcher m = optsPattern.matcher(input);
            String memoryOpts = null;
            while (m.find()) {
                memoryOpts = m.group();
            }
            if (memoryOpts != null) {
                int unit = 1;
                if (memoryOpts.toLowerCase().endsWith("k")) {
                    unit = 1024;
                } else if (memoryOpts.toLowerCase().endsWith("m")) {
                    unit = 1024 * 1024;
                } else if (memoryOpts.toLowerCase().endsWith("g")) {
                    unit = 1024 * 1024 * 1024;
                }
                memoryOpts = memoryOpts.replaceAll("[a-zA-Z]", "");
                Double result =  Double.parseDouble(memoryOpts) * unit / 1024.0 / 1024.0;
                return (result < 1.0) ? 1.0 : result;
            } else {
                return defaultValue;
            }
        } else {
            return defaultValue;
        }
    }