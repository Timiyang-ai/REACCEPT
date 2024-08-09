private void include (LineFilament pivot,
                          int pivotPos)
    {
        if (logger.isDebugEnabled() || pivot.isVip()) {
            logger.info("VIP {} include pivot:{} at pos:{}", this, pivot.getId(), pivotPos);

            if (pivot.isVip()) {
                setVip();
            }
        }

        LineFilament ancestor = (LineFilament) pivot.getAncestor();

        // Loop on all combs that involve this filament
        // Use a copy to avoid concurrent modification error
        List<FilamentComb> combs = new ArrayList<FilamentComb>(pivot.getCombs().values());

        for (FilamentComb comb : combs) {
            if (comb.isProcessed()) {
                continue;
            }

            comb.setProcessed(true);

            int deltaPos = pivotPos - comb.getIndex(pivot);
            logger.debug("{} deltaPos:{}", comb, deltaPos);

            // Dispatch content of comb to proper lines
            for (int i = 0; i < comb.getCount(); i++) {
                LineFilament fil = (LineFilament) comb.getFilament(i).getAncestor();
                LineCluster cluster = fil.getCluster();

                if (cluster == null) {
                    int pos = i + deltaPos;
                    FilamentLine line = lines.get(pos);

                    if (line == null) {
                        line = new FilamentLine(null);
                        lines.put(pos, line);
                    }

                    line.add(fil);

                    if (fil.isVip()) {
                        logger.info("VIP adding {} to {} at pos {}", fil, this, pos);
                        setVip();
                    }

                    fil.setCluster(this, pos);

                    if (fil != ancestor) {
                        include(fil, pos); // Recursively
                    }
                } else if (cluster.getAncestor() != this.getAncestor()) {
                    // Need to merge the two clusters
                    include(cluster, (i + deltaPos) - fil.getClusterPos());
                }
            }
        }
    }