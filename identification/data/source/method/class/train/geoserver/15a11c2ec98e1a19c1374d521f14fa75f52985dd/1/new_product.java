@Override
        public void encode(Object o) throws IllegalArgumentException {
            
            if (!(o instanceof DescribeCoverageType)) {
                throw new IllegalArgumentException(new StringBuffer("Not a GetCapabilitiesType: ")
                        .append(o).toString());
            }

            this.request = (DescribeCoverageType) o;

            final AttributesImpl attributes = WCS20Const.getDefaultNamespaces();

            // collect coverages
            List<String> badCoverageIds = new ArrayList<String>();
            List<LayerInfo> coverages = new ArrayList<LayerInfo>();

            for (String encodedCoverageId : (List<String>)request.getCoverageId()) {
                LayerInfo layer = NCNameResourceCodec.getCoverage(catalog, encodedCoverageId);
                if(layer != null) {
                    coverages.add(layer);
                } else {
                    badCoverageIds.add(encodedCoverageId);
                }
            }

            // any error?
            if( ! badCoverageIds.isEmpty() ) {
                String mergedIds = StringUtils.merge(badCoverageIds);
                throw new WCS20Exception("Could not find the requested coverage(s): " + mergedIds
                        , WCS20Exception.WCSExceptionCode.NoSuchCoverage, mergedIds);
            }

            // ok: build the response
            start("wcs:CoverageDescriptions", attributes);
            for (LayerInfo layer : coverages) {
                CoverageInfo ci = catalog.getCoverageByName(layer.prefixedName());
                try {
                    handleCoverageDescription(ci);
                } catch (Exception e) {
                    throw new RuntimeException(
                            "Unexpected error occurred during describe coverage xml encoding", e);
                }
            }
            end("wcs:CoverageDescriptions");
        }