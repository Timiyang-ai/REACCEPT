private String humanReadableByteCount(double bytes) {
            int unit = 1024;
            if (bytes < unit) return bytes + " B";
            int exp = (int) (Math.log(bytes) / Math.log(unit));
            String pre = "KMGTPE".charAt(exp - 1) + "i";
            return String.format("%.2f %sB", bytes / Math.pow(unit, exp), pre);
        }