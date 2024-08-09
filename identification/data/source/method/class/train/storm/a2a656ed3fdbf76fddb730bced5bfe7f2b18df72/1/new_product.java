public static Double parseJvmHeapMemByChildOpts(List<String> options, Double defaultValue) {
        if (options != null) {
            Pattern optsPattern = Pattern.compile("Xmx([0-9]+)([mkgMKG])");
            for (String option : options) {
                if (option == null) {
                    continue;
                }
                Matcher m = optsPattern.matcher(option);
                while (m.find()) {
                    int value = Integer.parseInt(m.group(1));
                    char unitChar = m.group(2).toLowerCase().charAt(0);
                    int unit;
                    switch (unitChar) {
                    case 'k':
                        unit = 1024;
                        break;
                    case 'm':
                        unit = 1024 * 1024;
                        break;
                    case 'g':
                        unit = 1024 * 1024 * 1024;
                        break;
                    default:
                        unit = 1;
                    }
                    Double result =  value * unit / 1024.0 / 1024.0;
                    return (result < 1.0) ? 1.0 : result;
                }
            }
            return defaultValue;
        } else {
            return defaultValue;
        }
    }