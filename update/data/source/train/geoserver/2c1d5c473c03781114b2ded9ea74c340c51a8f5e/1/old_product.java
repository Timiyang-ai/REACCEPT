protected boolean hasServiceSupport(String layerName, String serviceName) {
        LayerInfo linfo = getGeoServer().getCatalog().getLayerByName(layerName);
        if (linfo != null && linfo.getResource() != null && serviceName != null) {
            List<String> disabledServices =
                    DisabledServiceResourceFilter.disabledServices(linfo.getResource());
            return disabledServices.stream().noneMatch(d -> d.equalsIgnoreCase(serviceName));
        }
        // layer group and backward compatibility
        return true;
    }