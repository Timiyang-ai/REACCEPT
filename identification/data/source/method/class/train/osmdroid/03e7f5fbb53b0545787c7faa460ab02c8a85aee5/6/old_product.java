public void clip(final long pX0, final long pY0, final long pX1, final long pY1, int index) {
        if (!mPathMode) {
            if (isOnTheSameSideOut(pX0, pY0, pX1, pY1)) {
                return;
            }
        }
        if (isInClipArea(pX0, pY0)) {
            if (isInClipArea(pX1, pY1)) {
                nextVertex(pX0, pY0, index);
                nextVertex(pX1, pY1, index);
                return;
            }
            if (intersection(pX0, pY0, pX1, pY1)) {
                nextVertex(pX0, pY0, index);
                nextVertex(mOptimIntersection.x, mOptimIntersection.y, index);
                if (mPathMode) {
                    nextVertex(clipX(pX1), clipY(pY1), index);
                }
                return;
            }
            throw new RuntimeException("Cannot find expected mOptimIntersection for " + new RectL(pX0, pY0, pX1, pY1));
        }
        if (isInClipArea(pX1, pY1)) {
            if (intersection(pX0, pY0, pX1, pY1)) {
                if (mPathMode) {
                    nextVertex(clipX(pX0), clipY(pY0), index);
                }
                nextVertex(mOptimIntersection.x, mOptimIntersection.y, index);
                nextVertex(pX1, pY1, index);
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
                nextVertex(clipX(pX0), clipY(pY0), index);
            }
            nextVertex(start.x, start.y, index);
            nextVertex(end.x, end.y, index);
            if (mPathMode) {
                nextVertex(clipX(pX1), clipY(pY1), index);
            }
            return;
        }
        if (count == 1) {
            if (mPathMode) {
                nextVertex(clipX(pX0), clipY(pY0), index);
                nextVertex(mOptimIntersection1.x, mOptimIntersection1.y, index);
                nextVertex(clipX(pX1), clipY(pY1), index);
            }
            return;
        }
        if (count == 0) {
            if (mPathMode) {
                nextVertex(clipX(pX0), clipY(pY0), index);
                final int corner = getClosestCorner(pX0, pY0, pX1, pY1);
                nextVertex(cornerX[corner], cornerY[corner], index);
                nextVertex(clipX(pX1), clipY(pY1), index);
            }
            return;
        }
        throw new RuntimeException("Impossible mOptimIntersection count (" + count + ")");
    }