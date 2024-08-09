    private static boolean dependsOn(CoordinatorLayout.LayoutParams lpChild,
            CoordinatorLayout.LayoutParams lpDependency, CoordinatorLayout col,
            View child, View dependency) {
        child.setLayoutParams(lpChild);
        dependency.setLayoutParams(lpDependency);
        return lpChild.dependsOn(col, child, dependency);
    }