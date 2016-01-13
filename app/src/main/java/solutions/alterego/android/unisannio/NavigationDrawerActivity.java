package solutions.alterego.android.unisannio;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import solutions.alterego.android.unisannio.utils.DeveloperError;

public abstract class NavigationDrawerActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static final String EXTRA_FROM_NAV_DRAWER = BuildConfig.APPLICATION_ID + ".extra.from_nav_drawer";

    private static final Bundle EXTRAS_FROM_NAV_DRAWER;

    static {
        EXTRAS_FROM_NAV_DRAWER = new Bundle(1);
        EXTRAS_FROM_NAV_DRAWER.putBoolean(EXTRA_FROM_NAV_DRAWER, true);
    }

    private DrawerLayout drawerLayout;

    private NavigationView navigationView;

    private ViewGroup rootContainer;

    @Override
    @CallSuper
    public void setContentView(@LayoutRes int layoutResID) {
        ensureDrawerLayout();
        rootContainer.removeAllViews();
        getLayoutInflater().inflate(layoutResID, rootContainer, true);
        findAndSetAppbar();
    }

    @Override
    @CallSuper
    public void setContentView(View view) {
        setContentView(view, null);
    }

    @Override
    @CallSuper
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        ensureDrawerLayout();
        rootContainer.removeAllViews();
        if (params == null) {
            rootContainer.addView(view);
        } else {
            rootContainer.addView(view, params);
        }
        findAndSetAppbar();
    }

    @SuppressLint("InflateParams")      // Inflating root content view, we don't have layout params for it
    private void ensureDrawerLayout() {
        if (drawerLayout != null) {
            return;
        }

        Window window = getWindow();
        drawerLayout = (DrawerLayout) window.getLayoutInflater().inflate(R.layout.base_activity_navigation_drawer, null);
        rootContainer = (ViewGroup) drawerLayout.findViewById(R.id.root_container);
        navigationView = (NavigationView) drawerLayout.findViewById(R.id.drawer_menu);

        window.setContentView(drawerLayout);
        setupNavigationDrawer();
    }

    private void setupNavigationDrawer() {
        navigationView.setCheckedItem(getNavigationDrawerMenuIdForThisActivity());
        navigationView.setNavigationItemSelectedListener(this);
        hackToHideNavDrawerHeaderRipple();
    }

    private void hackToHideNavDrawerHeaderRipple() {
        // TODO remove this when the issue is fixed
        // See https://code.google.com/p/android/issues/detail?id=176400
        View navigationHeader = findViewById(R.id.navigation_header);
        if (navigationHeader != null) {
            ((View) navigationHeader.getParent()).setOnClickListener(
                v -> {
                    // Do nothing
                }
            );
        }
    }

    @Override
    @CallSuper
    public boolean onNavigationItemSelected(final MenuItem item) {
        if (item.getItemId() == getNavigationDrawerMenuIdForThisActivity()) {
            // Do nothing: we're already there
            drawerLayout.closeDrawer(navigationView);
            return true;
        }

        drawerLayout.setDrawerListener(new NavigateAfterDrawerCloseListener(item));
        drawerLayout.closeDrawer(navigationView);
        return true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        drawerLayout.setDrawerListener(null);
    }

    @Override
    public void onBackPressed() {
        if (onBackPressedShouldCloseDrawer()) {
            drawerLayout.closeDrawer(navigationView);
        } else {
            super.onBackPressed();
        }
    }

    private boolean onBackPressedShouldCloseDrawer() {
        return drawerLayout.isDrawerOpen(navigationView);
    }

    @IdRes
    protected abstract int getNavigationDrawerMenuIdForThisActivity();

    protected final void openNavigationDrawer() {
        drawerLayout.openDrawer(navigationView);
    }

    private class NavigateAfterDrawerCloseListener implements DrawerLayout.DrawerListener {

        private final MenuItem item;

        public NavigateAfterDrawerCloseListener(MenuItem item) {
            this.item = item;
        }

        @Override
        public void onDrawerSlide(View drawerView, float slideOffset) {
            // No-op
        }

        @Override
        public void onDrawerOpened(View drawerView) {
            // No-op
        }

        @Override
        public void onDrawerClosed(View drawerView) {
            drawerLayout.setDrawerListener(null);

            switch (item.getItemId()) {
                case R.id.drawer_ateneo_avvisi:
                    navigate().toAteneo();
                    break;
                case R.id.drawer_ateneo_avvisi_studenti:
                    navigate().toAteneoStudenti();
                    break;
                case R.id.drawer_ingegneria_avvisi_studenti:
                    navigate().toIngegneria();
                    break;
                case R.id.drawer_ingegneria_avvisi_dipartimento:
                    navigate().toIngegneria();
                    break;
                case R.id.cercapersone_ingegneria:
                    navigate().toIngegneriaCercapersone();
                    break;
                default:
                    throw new DeveloperError("Menu item " + item + " not supported");
            }
        }

        @Override
        public void onDrawerStateChanged(int newState) {
            // No-op
        }
    }
}
