public void clip(final long pX0, final long pY0, final long pX1, final long pY1) {
        if (isInClipArea(pX0, pY0)) {
            if (isInClipArea(pX1, pY1)) {
                nextVertex(pX0, pY0);
                nextVertex(pX1, pY1);
                return;
            }
            if (intersection(pX0, pY0, pX1, pY1)) {
                nextVertex(pX0, pY0);
                nextVertex(mOptimIntersection.x, mOptimIntersection.y);
                if (mPathMode) {
                    nextVertex(clipX(pX1), clipY(pY1));
                }
                return;
            }
            throw new RuntimeException("Cannot find expected mOptimIntersection for " + new RectL(pX0, pY0, pX1, pY1));
        }
        if (isInClipArea(pX1, pY1)) {
            if (intersection(pX0, pY0, pX1, pY1)) {
                if (mPathMode) {
                    nextVertex(clipX(pX0), clipY(pY0));
                }
                nextVertex(mOptimIntersection.x, mOptimIntersection.y);
                nextVertex(pX1, pY1);
                return;
            }
            throw new RuntimeException("Cannot find expected mOptimIntersection for " + new RectL(pX0, pY0, pX1, pY1));
        }
        // no point is on the screen
        int count = 0;
        if (intersection(pX0, pY0, pX1, pY1, mXMin, mYMin, mXMin, mYMax)) { // x mClipMin segment
            final PointL point = count ++ == 0 ? mOptimIntersection1 : mOptimIntersection2;
            point.set(mOptimIntersection);
        }
        if (intersection(pX0, pY0, pX1, pY1, mXMax, mYMin, mXMax, mYMax)) { // x mClipMax segment
            final PointL point = count ++ == 0 ? mOptimIntersection1 : mOptimIntersection2;
            point.set(mOptimIntersection);
        }
        if (intersection(pX0, pY0, pX1, pY1, mXMin, mYMin, mXMax, mYMin)) { // y mClipMin segment
            final PointL point = count ++ == 0 ? mOptimIntersection1 : mOptimIntersection2;
            point.set(mOptimIntersection);
        }
        if (intersection(pX0, pY0, pX1, pY1, mXMin, mYMax, mXMax, mYMax)) { // y mClipMax segment
            final PointL point = count ++ == 0 ? mOptimIntersection1 : mOptimIntersection2;
            point.set(mOptimIntersection);
        }
        if (count == 2) {
            final double distance1 = Distance.getSquaredDistanceToPoint(
                    mOptimIntersection1.x, mOptimIntersection1.y, pX0, pY0);
            final double distance2 = Distance.getSquaredDistanceToPoint(
                    mOptimIntersection2.x, mOptimIntersection2.y, pX0, pY0);
            final PointL start = distance1 < distance2 ? mOptimIntersection1 : mOptimIntersection2;
            final PointL end =  distance1 < distance2 ? mOptimIntersection2 : mOptimIntersection1;
            if (mPathMode) {
                nextVertex(clipX(pX0), clipY(pY0));
            }
            nextVertex(start.x, start.y);
            nextVertex(end.x, end.y);
            if (mPathMode) {
                nextVertex(clipX(pX1), clipY(pY1));
            }
            return;
        }
        if (count == 1) {
            if (mPathMode) {
                nextVertex(clipX(pX0), clipY(pY0));
                nextVertex(mOptimIntersection1.x, mOptimIntersection1.y);
                nextVertex(clipX(pX1), clipY(pY1));
            }
            return;
        }
        if (count == 0) {
            if (mPathMode) {
                nextVertex(clipX(pX0), clipY(pY0));
                final int corner = getClosestCorner(pX0, pY0, pX1, pY1);
                nextVertex(cornerX[corner], cornerY[corner]);
                nextVertex(clipX(pX1), clipY(pY1));
            }
            return;
        }
        throw new RuntimeException("Impossible mOptimIntersection count (" + count + ")");
    }