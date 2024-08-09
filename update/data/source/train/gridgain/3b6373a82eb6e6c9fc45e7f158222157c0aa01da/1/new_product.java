public static String toJson(HistogramMetric metric) {
        GridStringBuilder json = new GridStringBuilder();

        json.a("{\"bounds\":[");

        long[] bounds = metric.bounds();

        for (int i = 0; i < bounds.length; i++) {
            json.a(bounds[i]);

            if (i < bounds.length - 1)
                json.a(",");
        }

        json.a("],\"values\":[");

        long[] values = metric.value();

        for (int i = 0; i < values.length; i++) {
            long from = (i == 0 ? 0 : bounds[i - 1]);
            long to = (i == values.length - 1 ? -1 : bounds[i]);
            long val = values[i];

            json.a(i == 0 ? "{\"fromInclusive\":" : "{\"fromExclusive\":").a(from);

            if (to >= 0)
                json.a(",\"toInclusive\":").a(to);

            json
                .a(",\"value\":")
                .a(val)
                .a("}");

            if (i < values.length - 1)
                json.a(",");
        }

        json.a("]}");

        return json.toString();
    }