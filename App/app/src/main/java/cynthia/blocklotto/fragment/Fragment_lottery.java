package cynthia.blocklotto.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import cynthia.blocklotto.R;
import cynthia.blocklotto.adapter.Adapter_view_page;

public class Fragment_lottery extends Fragment {

    private TabLayout tabLayout;
    //private AppBarLayout appBarLayout;
    private ViewPager viewPager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_lottery, container, false) ;

        tabLayout = (TabLayout) view.findViewById(R.id.tabLottery);
       // appBarLayout = (ViewPager) view.findViewById(R.id.viewPagerLottery);
        viewPager = (ViewPager) view.findViewById(R.id.viewPagerLottery);

        Adapter_view_page adapter = new Adapter_view_page(getChildFragmentManager());

        adapter.addFragment(new Fragment_next_lottery(), "Próximos");
        adapter.addFragment(new Fragment_pending_lottery(), "Pendientes");
        adapter.addFragment(new Fragment_archived_lottery(), "Celebrados");
        setHasOptionsMenu(true);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        return view;
    }
}
