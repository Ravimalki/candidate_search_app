package com.example.candidate_search_test_app.candidates;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.example.candidate_search_test_app.candidates.CandidateList.CandidateListFragment;
import com.example.candidate_search_test_app.candidates.SelectedList.SelectedListFragment;
import com.example.candidate_search_test_app.R;
import com.example.candidate_search_test_app.databinding.CandidateViewBinding;
import com.google.android.material.tabs.TabLayout;

/**
 * Created by Koshini Bulathsinhala
 */

public class CandidateFragment extends Fragment {

    private CandidateViewBinding binding;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = CandidateViewBinding.inflate(inflater, container, false);
        initComponents();
        return binding.getRoot();
    }

    /**
     * initiate components
     */
    private void initComponents() {
        setupViewPager(binding.editView);
    }

    /**
     * setup viewpager to tablayout
     * @param viewPager - viewpager component
     */
    private void setupViewPager(ViewPager2 viewPager) {
        binding.editView.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }
        });

        binding.tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                binding.editView.setCurrentItem(tab.getPosition(), false);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        binding.tabLayout.addTab(binding.tabLayout.newTab().setText(R.string.tab_1));
        binding.tabLayout.addTab((binding.tabLayout.newTab().setText(R.string.tab_2)));
        CandidateTabAdapter adapter = new CandidateTabAdapter(getChildFragmentManager(), getLifecycle());
        viewPager.setAdapter(adapter);
    }

    static class CandidateTabAdapter extends FragmentStateAdapter {
        public CandidateTabAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
            super(fragmentManager, lifecycle);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            switch (position) {
                case 0:
                    return new CandidateListFragment();
                case 1:
                    return new SelectedListFragment();
                default:
                    return null;
            }
        }

        @Override
        public int getItemCount() {
            return 2;
        }
    }

}