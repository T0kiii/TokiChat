package com.tokiapps.tokichat.activities;


import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.LinearLayout;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.mancj.materialsearchbar.MaterialSearchBar;
import com.tokiapps.tokichat.R;
import com.tokiapps.tokichat.adapters.ViewPagerAdapter;
import com.tokiapps.tokichat.fragments.ChatsFragment;
import com.tokiapps.tokichat.fragments.ChildrenFragment;
import com.tokiapps.tokichat.fragments.ContacsFragment;
import com.tokiapps.tokichat.fragments.StatusFragment;
import com.tokiapps.tokichat.providers.AuthProvider;

public class HomeActivity extends AppCompatActivity implements MaterialSearchBar.OnSearchActionListener{

    MaterialSearchBar mSearchBar;
    AuthProvider mAuthProvider;
    TabLayout mTabLayout;
    ViewPager mViewPager;

    ChatsFragment mChatsFragment;
    ChildrenFragment mChildrenFragment;
    ContacsFragment mContactsFragments;
    StatusFragment mStatusFragment;

    //int mTabSelected = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mSearchBar = findViewById(R.id.searchBar);
        mTabLayout = findViewById(R.id.tabLayout);
        mViewPager = findViewById(R.id.viewPager);

        mViewPager.setOffscreenPageLimit(3);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        mChatsFragment = new ChatsFragment();
        mChildrenFragment = new ChildrenFragment();
        mContactsFragments = new ContacsFragment();
        mStatusFragment = new StatusFragment();
        adapter.addFragment(mChatsFragment, "CHATS");
        //adapter.addFragment(mStatusFragment, "ESTADOS");
        adapter.addFragment(mContactsFragments, "CONTACTOS");
        adapter.addFragment(mChildrenFragment, "HIJOS");

        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);
        //mViewPager.setCurrentItem(mTabSelected);
        setupTabIcon();

        mSearchBar.setOnSearchActionListener(this);
        mSearchBar.inflateMenu(R.menu.main_menu);
        mSearchBar.getMenu().setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.itemSignOut) {
                    signOut();
                }
                return true;
            }
        });

        mAuthProvider = new AuthProvider();
    }

    private void setupTabIcon() {
        mTabLayout.getTabAt(0).setIcon(R.drawable.ic_chat);
        mTabLayout.getTabAt(1).setIcon(R.drawable.ic_person);
        mTabLayout.getTabAt(2).setIcon(R.drawable.ic_kid);
        LinearLayout linearLayout = ((LinearLayout) ((LinearLayout) mTabLayout.getChildAt(0)).getChildAt(0));
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) linearLayout.getLayoutParams();
        layoutParams.weight = 0.5f;
        linearLayout.setLayoutParams(layoutParams);
    }

    private void signOut() {
        mAuthProvider.signOut();
        Intent intent = new Intent(HomeActivity.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    public void onSearchStateChanged(boolean enabled) {

    }

    @Override
    public void onSearchConfirmed(CharSequence text) {

    }

    @Override
    public void onButtonClicked(int buttonCode) {

    }
}