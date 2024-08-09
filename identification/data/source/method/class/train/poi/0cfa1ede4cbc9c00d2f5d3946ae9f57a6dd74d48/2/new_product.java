protected void read(POIXMLFactory factory, Map<PackagePart, POIXMLDocumentPart> context) throws OpenXML4JException {
        PackagePart pp = getPackagePart();
        // add mapping a second time, in case of initial caller hasn't done so
        POIXMLDocumentPart otherChild = context.put(pp, this);
        if (otherChild != null && otherChild != this) {
            throw new POIXMLException("Unique PackagePart-POIXMLDocumentPart relation broken!");
        }

        if (!pp.hasRelationships()) return;

        PackageRelationshipCollection rels = packagePart.getRelationships();
        List<POIXMLDocumentPart> readLater = new ArrayList<>();

        // scan breadth-first, so parent-relations are hopefully the shallowest element
        for (PackageRelationship rel : rels) {
            if(rel.getTargetMode() == TargetMode.INTERNAL){
                URI uri = rel.getTargetURI();

                // check for internal references (e.g. '#Sheet1!A1')
                PackagePartName relName;
                if(uri.getRawFragment() != null) {
                    relName = PackagingURIHelper.createPartName(uri.getPath());
                } else {
                    relName = PackagingURIHelper.createPartName(uri);
                }

                final PackagePart p = packagePart.getPackage().getPart(relName);
                if (p == null) {
                    logger.log(POILogger.ERROR, "Skipped invalid entry " + rel.getTargetURI());
                    continue;
                }

                POIXMLDocumentPart childPart = context.get(p);
                if (childPart == null) {
                    childPart = factory.createDocumentPart(this, p);
                    childPart.parent = this;
                    // already add child to context, so other children can reference it
                    context.put(p, childPart);
                    readLater.add(childPart);
                }

                addRelation(rel,childPart);
            }
        }

        for (POIXMLDocumentPart childPart : readLater) {
            childPart.read(factory, context);
        }
    }