boolean dependsOn(CoordinatorLayout parent, View child, View dependency) {
            return dependency == mAnchorDirectChild
                    || (mBehavior != null && mBehavior.layoutDependsOn(parent, child, dependency));
        }