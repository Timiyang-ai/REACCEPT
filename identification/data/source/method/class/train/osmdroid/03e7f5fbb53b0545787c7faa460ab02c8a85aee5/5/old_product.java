public void clip(final RectL pSegment) {
        if (isInClipArea(pSegment.left, pSegment.top)) {
            if (isInClipArea(pSegment.right, pSegment.bottom)) {
                add(pSegment.left, pSegment.top);
                add(pSegment.right, pSegment.bottom);
                return;
            }
            if (intersection(pSegment)) {
                add(pSegment.left, pSegment.top);
                add(mOptimIntersection.x, mOptimIntersection.y);
                add(clipX(pSegment.right), clipY(pSegment.bottom));
                return;
            }
            throw new RuntimeException("Cannot find expected mOptimIntersection for " + pSegment);
        }
        if (isInClipArea(pSegment.right, pSegment.bottom)) {
            if (intersection(pSegment)) {
                add(clipX(pSegment.left), clipY(pSegment.top));
                add(mOptimIntersection.x, mOptimIntersection.y);
                add(pSegment.right, pSegment.bottom);
                return;
            }
            throw new RuntimeException("Cannot find expected mOptimIntersection for " + pSegment);
        }
        // no point is on the screen
        int count = 0;
        if (intersection(pSegment, mXMin, mYMin, mXMin, mYMax)) { // x mClipMin segment
            final PointL point = count ++ == 0 ? mOptimIntersection1 : mOptimIntersection2;
            point.set(mOptimIntersection);
        }
        if (intersection(pSegment, mXMax, mYMin, mXMax, mYMax)) { // x mClipMax segment
            final PointL point = count ++ == 0 ? mOptimIntersection1 : mOptimIntersection2;
            point.set(mOptimIntersection);
        }
        if (intersection(pSegment, mXMin, mYMin, mXMax, mYMin)) { // y mClipMin segment
            final PointL point = count ++ == 0 ? mOptimIntersection1 : mOptimIntersection2;
            point.set(mOptimIntersection);
        }
        if (intersection(pSegment, mXMin, mYMax, mXMax, mYMax)) { // y mClipMax segment
            final PointL point = count ++ == 0 ? mOptimIntersection1 : mOptimIntersection2;
            point.set(mOptimIntersection);
        }
        if (count == 2) {
            final double distance1 = Distance.getSquaredDistanceToPoint(
                    mOptimIntersection1.x, mOptimIntersection1.y, pSegment.left, pSegment.top);
            final double distance2 = Distance.getSquaredDistanceToPoint(
                    mOptimIntersection2.x, mOptimIntersection2.y, pSegment.left, pSegment.top);
            final PointL start = distance1 < distance2 ? mOptimIntersection1 : mOptimIntersection2;
            final PointL end =  distance1 < distance2 ? mOptimIntersection2 : mOptimIntersection1;
            add(clipX(pSegment.left), clipY(pSegment.top));
            add(start.x, start.y);
            add(end.x, end.y);
            add(clipX(pSegment.right), clipY(pSegment.bottom));
            return;
        }
        if (count == 1) {
            add(clipX(pSegment.left), clipY(pSegment.top));
            add(mOptimIntersection1.x, mOptimIntersection1.y);
            add(clipX(pSegment.right), clipY(pSegment.bottom));
            return;
        }
        if (count == 0) {
            add(clipX(pSegment.left), clipY(pSegment.top));
            final int corner = getClosestCorner(pSegment);
            add(cornerX[corner], cornerY[corner]);
            add(clipX(pSegment.right), clipY(pSegment.bottom));
            return;
        }
        throw new RuntimeException("Impossible mOptimIntersection count (" + count + ")");
    }