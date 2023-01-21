package com.danielme.android.navigationdrawer;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.google.android.material.navigation.NavigationView;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        DrawerLayout.DrawerListener {

  private DrawerLayout drawerLayout;
  private NavigationView navigationView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_home);

    Toolbar toolbar = findViewById(R.id.toolbar);
    setSupportActionBar(findViewById(R.id.toolbar));

    setupDrawer(toolbar);
    setupNavigationView();
    setupHeader();
  }

  private void setupDrawer(Toolbar toolbar) {
    drawerLayout = findViewById(R.id.drawer_layout);
    ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
            this, drawerLayout, toolbar, R.string.navigation_drawer_open,
            R.string.navigation_drawer_close);
    drawerLayout.addDrawerListener(toggle);
    toggle.syncState();

    drawerLayout.addDrawerListener(this);
  }

  private void setupHeader() {
    View header = navigationView.getHeaderView(0);
    header.findViewById(R.id.header_title).setOnClickListener(view -> Toast.makeText(
            HomeActivity.this,
            getString(R.string.title_click),
            Toast.LENGTH_SHORT).show());
  }

  private void setupNavigationView() {
    navigationView = findViewById(R.id.navigation_view);
    navigationView.setNavigationItemSelectedListener(this);
    MenuItem menuItem = navigationView.getMenu().getItem(0);
    onNavigationItemSelected(menuItem);
    menuItem.setChecked(true);
  }

  @Override
  public void onBackPressed() {
    if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
      drawerLayout.closeDrawer(GravityCompat.START);
    } else {
      super.onBackPressed();
    }
  }

  @Override
  public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
    int title = getTitle(menuItem);
    showFragment(title);
    drawerLayout.closeDrawer(GravityCompat.START);
    return true;
  }

  private int getTitle(@NonNull MenuItem menuItem) {
    switch (menuItem.getItemId()) {
      case R.id.nav_camera:
        return R.string.menu_camera;
      case R.id.nav_gallery:
        return R.string.menu_gallery;
      case R.id.nav_manage:
        return R.string.menu_tools;
      case R.id.nav_share:
        return R.string.menu_share;
      case R.id.nav_send:
        return R.string.menu_send;
      default:
        throw new IllegalArgumentException("menu option not implemented!!");
    }
  }

  private void showFragment(@StringRes int title) {
    Fragment fragment = HomeContentFragment.newInstance(getString(title));
    getSupportFragmentManager()
            .beginTransaction()
            .setCustomAnimations(R.anim.nav_enter, R.anim.nav_exit)
            .replace(R.id.home_content, fragment)
            .commit();

    setTitle(getString(title));
  }

  @Override
  public void onDrawerSlide(@NonNull View view, float v) {
    //cambio en la posición del drawer
  }

  @Override
  public void onDrawerOpened(@NonNull View view) {
    //el drawer se ha abierto completamente
    Toast.makeText(this, getString(R.string.navigation_drawer_open),
            Toast.LENGTH_SHORT).show();
  }

  @Override
  public void onDrawerClosed(@NonNull View view) {
    //el drawer se ha cerrado completamente
  }

  @Override
  public void onDrawerStateChanged(int i) {
    //cambio de estado, puede ser STATE_IDLE, STATE_DRAGGING or STATE_SETTLING
  }

}
