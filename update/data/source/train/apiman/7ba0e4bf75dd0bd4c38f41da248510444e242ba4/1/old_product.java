public static Map<String, UsageDataPoint> generateHistogramSkeleton(UsageHistogramBean rval, DateTime from, DateTime to,
            UsageHistogramIntervalType interval) {
        Map<String, UsageDataPoint> index = new HashMap<>();

        Calendar fromCal = from.toGregorianCalendar();
        Calendar toCal = to.toGregorianCalendar();

        switch(interval) {
            case day:
                fromCal.set(Calendar.HOUR_OF_DAY, 0);
                fromCal.set(Calendar.MINUTE, 0);
                fromCal.set(Calendar.SECOND, 0);
                fromCal.set(Calendar.MILLISECOND, 0);
                break;
            case hour:
                fromCal.set(Calendar.MINUTE, 0);
                fromCal.set(Calendar.SECOND, 0);
                fromCal.set(Calendar.MILLISECOND, 0);
                break;
            case minute:
                fromCal.set(Calendar.SECOND, 0);
                fromCal.set(Calendar.MILLISECOND, 0);
                break;
            case month:
                fromCal.set(Calendar.DAY_OF_MONTH, 1);
                fromCal.set(Calendar.HOUR_OF_DAY, 0);
                fromCal.set(Calendar.MINUTE, 0);
                fromCal.set(Calendar.SECOND, 0);
                fromCal.set(Calendar.MILLISECOND, 0);
                break;
            case week:
                fromCal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
                fromCal.set(Calendar.HOUR_OF_DAY, 0);
                fromCal.set(Calendar.MINUTE, 0);
                fromCal.set(Calendar.SECOND, 0);
                fromCal.set(Calendar.MILLISECOND, 0);
                break;
            default:
                break;
        }

        while (fromCal.before(toCal)) {
            String label = formatDateWithMillis(fromCal);
            UsageDataPoint point = new UsageDataPoint(label, 0L);
            rval.getData().add(point);
            index.put(label, point);
            switch (interval) {
                case day:
                    fromCal.add(Calendar.DAY_OF_YEAR, 1);
                    break;
                case hour:
                    fromCal.add(Calendar.HOUR_OF_DAY, 1);
                    break;
                case minute:
                    fromCal.add(Calendar.MINUTE, 1);
                    break;
                case month:
                    fromCal.add(Calendar.MONTH, 1);
                    break;
                case week:
                    fromCal.add(Calendar.WEEK_OF_YEAR, 1);
                    break;
                default:
                    break;
            }
        }

        return index;

    }