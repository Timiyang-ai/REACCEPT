String humanReadableByteCount(double bytes) {
            int unit = 1024;
            if (bytes < unit) return decimalOrNan(bytes) + " B";
            int exp = (int) (Math.log(bytes) / Math.log(unit));
            String pre = "KMGTPE".charAt(exp - 1) + "i";
            return decimalOrNan(bytes / Math.pow(unit, exp)) + " " + pre + "B";
        }