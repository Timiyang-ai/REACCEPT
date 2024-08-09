public synchronized void removeEvidence(EvidenceType type, Evidence e) {
        if (null != type) {
            switch (type) {
                case VENDOR:
                    vendors.remove(e);
                    break;
                case PRODUCT:
                    products.remove(e);
                    break;
                case VERSION:
                    versions.remove(e);
                    break;
                default:
                    break;
            }
        }
    }