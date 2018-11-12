package cynthia.blocklotto.fragment.lottery;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import cynthia.blocklotto.R;
import cynthia.blocklotto.adapter.lottery.Adapter_view_page;

public class Fragment_lottery extends Fragment {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private Adapter_view_page adapter;
    private TextView balance;
    private Button refreshBalance;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_lottery, container, false) ;

        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);

        balance = view.findViewById(R.id.balanceLottery);
        refreshBalance = view.findViewById(R.id.refresh_balance);
        tabLayout = (TabLayout) view.findViewById(R.id.tabLottery);
        viewPager = (ViewPager) view.findViewById(R.id.viewPagerLottery);
        adapter = new Adapter_view_page(getChildFragmentManager());

        controlButton();
        setHasOptionsMenu(true);
        addFragment();
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        return view;
    }

    private void controlButton(){
        refreshBalance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                balance.setText("6 BTC");
            }
        });
    }

    private void addFragment(){
        adapter.addFragment(new Fragment_next_lottery(), "Próximos");
        adapter.addFragment(new Fragment_pending_lottery(), "Pendientes");
        adapter.addFragment(new Fragment_archived_lottery(), "Celebrados");
    }

}
