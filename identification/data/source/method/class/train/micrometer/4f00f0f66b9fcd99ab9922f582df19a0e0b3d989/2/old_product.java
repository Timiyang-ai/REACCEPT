String humanReadableByteCount(double bytes) {
            int unit = 1024;
            if (bytes < unit) return decimalOrWhole(bytes) + " B";
            int exp = (int) (Math.log(bytes) / Math.log(unit));
            String pre = "KMGTPE".charAt(exp - 1) + "i";
            return decimalOrWhole(bytes / Math.pow(unit, exp)) + " " + pre + "B";
        }