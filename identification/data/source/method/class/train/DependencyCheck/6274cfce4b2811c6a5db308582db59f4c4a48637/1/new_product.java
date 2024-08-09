protected String buildSearch(String vendor, String product, String version,
            Set<String> vendorWeighting, Set<String> produdctWeightings) {

        StringBuilder sb = new StringBuilder(vendor.length() + product.length()
                + version.length() + Fields.PRODUCT.length() + Fields.VERSION.length()
                + Fields.VENDOR.length() + STRING_BUILDER_BUFFER);

        if ("".equals(version)) {
            return null;
        }

        if (!appendWeightedSearch(sb, Fields.PRODUCT, product, produdctWeightings)) {
            return null;
        }
        sb.append(" AND ");
        if (!appendWeightedSearch(sb, Fields.VENDOR, vendor, vendorWeighting)) {
            return null;
        }
        sb.append(" AND ");

        sb.append(Fields.VERSION).append(":(");
        if (sb.indexOf("^") > 0) {
            //if we have a weighting on something else, reduce the weighting on the version a lot
            for (String v : version.split(" ")) {
                LuceneUtils.appendEscapedLuceneQuery(sb, cleanseText(v));
                sb.append("^0.2 ");
            }
        } else {
            //LuceneUtils.appendEscapedLuceneQuery(sb, version);
            //if we have a weighting on something else, reduce the weighting on the version a lot
            for (String v : version.split(" ")) {
                LuceneUtils.appendEscapedLuceneQuery(sb, cleanseText(v));
                sb.append("^0.7 ");
            }
        }
        sb.append(")");

        return sb.toString();
    }