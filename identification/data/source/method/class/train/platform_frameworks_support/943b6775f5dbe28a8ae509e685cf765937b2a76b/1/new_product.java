boolean dependsOn(CoordinatorLayout parent, View child, View dependency) {
            return dependency == mAnchorDirectChild
                    || shouldDodge(dependency, ViewCompat.getLayoutDirection(parent))
                    || (mBehavior != null && mBehavior.layoutDependsOn(parent, child, dependency));
        }