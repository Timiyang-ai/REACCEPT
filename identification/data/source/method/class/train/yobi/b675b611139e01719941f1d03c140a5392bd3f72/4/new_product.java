public Hunks getHunks() {
        if (hunks != null) {
            return hunks;
        }

        if (editList == null) {
            return null;
        }

        int size = 0;
        int lines = 0;

        hunks = new Hunks();

        for (int curIdx = 0; curIdx < editList.size();) {
            Hunk hunk = new Hunk();
            Edit curEdit = editList.get(curIdx);
            final int endIdx = findCombinedEnd(editList, curIdx);
            final Edit endEdit = editList.get(endIdx);

            int aCur = Math.max(0, curEdit.getBeginA() - context);
            int bCur = Math.max(0, curEdit.getBeginB() - context);
            final int aEnd = Math.min(a.size(), endEdit.getEndA() + context);
            final int bEnd = Math.min(b.size(), endEdit.getEndB() + context);

            hunk.beginA = aCur;
            hunk.endA = aEnd;
            hunk.beginB = bCur;
            hunk.endB = bEnd;

            while (aCur < aEnd || bCur < bEnd) {
                if (aCur < curEdit.getBeginA() || endIdx + 1 < curIdx) {
                    hunk.lines.add(new DiffLine(this, DiffLineType.CONTEXT, aCur, bCur,
                            a.getString(aCur)));
                    aCur++;
                    bCur++;
                } else if (aCur < curEdit.getEndA()) {
                    hunk.lines.add(new DiffLine(this, DiffLineType.REMOVE, aCur, bCur,
                            a.getString(aCur)));
                    aCur++;
                } else if (bCur < curEdit.getEndB()) {
                    hunk.lines.add(new DiffLine(this, DiffLineType.ADD, aCur, bCur,
                            b.getString(bCur)));
                    bCur++;
                }

                if (end(curEdit, aCur, bCur) && ++curIdx < editList.size())
                    curEdit = editList.get(curIdx);
            }

            if (interestLine != null && interestSide != null) {
                boolean added = false;
                switch(interestSide) {
                    case A:
                        if (hunk.beginA <= interestLine && hunk.endA >= interestLine) {
                            hunks.add(hunk);
                            size += hunk.size();
                            lines += hunk.lines.size();
                            added = true;
                        }
                        break;
                    case B:
                        if (hunk.beginB <= interestLine && hunk.endB >= interestLine) {
                            hunks.add(hunk);
                            size += hunk.size();
                            lines += hunk.lines.size();
                            added = true;
                        }
                        break;
                    default:
                        break;
                }
                if (added) {
                    break;
                }
            } else {
                hunks.add(hunk);
                size += hunk.size();
                lines += hunk.lines.size();
            }

            if (size > SIZE_LIMIT || lines > LINE_LIMIT) {
                hunks = new SizeExceededHunks();
                return hunks;
            }
        }

        hunks.size = size;
        hunks.lines = lines;

        return hunks;
    }