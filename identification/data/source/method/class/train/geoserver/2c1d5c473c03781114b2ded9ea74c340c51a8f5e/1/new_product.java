private List<ExternalLink> commonFormatLinks(PreviewLayer layer) {
        List<ExternalLink> links = new ArrayList<>();
        List<CommonFormatLink> formats =
                getGeoServerApplication().getBeansOfType(CommonFormatLink.class);
        Collections.sort(formats);
        for (CommonFormatLink link : formats) {
            links.add(link.getFormatLink(layer));
        }
        return links;
    }