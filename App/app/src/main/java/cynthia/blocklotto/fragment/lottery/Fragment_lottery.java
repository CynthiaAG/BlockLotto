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
import android.widget.ProgressBar;
import android.widget.TextView;

import cynthia.blocklotto.R;
import cynthia.blocklotto.conection.ResultFromJson;
import cynthia.blocklotto.adapter.lottery.Adapter_view_page;
import cynthia.blocklotto.conection.Accessor;
import cynthia.blocklotto.conection.AccessorResponse;

public class Fragment_lottery extends Fragment implements AccessorResponse {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private Adapter_view_page adapter;
    private TextView balance;
    private Button refreshBalance;
    private ProgressBar progressBar;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_lottery, container, false) ;

        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);

        progressBar = view.findViewById(R.id.progress_lottery);
        balance = view.findViewById(R.id.balanceLottery);
        refreshBalance = view.findViewById(R.id.refresh_balance);
        tabLayout = (TabLayout) view.findViewById(R.id.tabLottery);
        viewPager = (ViewPager) view.findViewById(R.id.viewPagerLottery);

        balance.setText("--");
        adapter = new Adapter_view_page(getChildFragmentManager());
        controlButton();
        setHasOptionsMenu(true);
        addFragment();
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        getBalance();

        return view;
    }

    private void getBalance(){
        refreshBalance.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.VISIBLE);
        Accessor con = new Accessor();
        con.accessorResponse =this;
        con.getBalance(getContext());

    }

    private void controlButton(){
        refreshBalance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getBalance();
            }
        });
    }

    private void addFragment(){
        adapter.addFragment(new Fragment_next_lottery(), "Próximos");
        adapter.addFragment(new Fragment_pending_lottery(), "Pendientes");
        adapter.addFragment(new Fragment_archived_lottery(), "Celebrados");
    }

    @Override
    public void processFinish(String output) {
        ResultFromJson resultFromJson = new ResultFromJson();
        if(output == null){
            refreshBalance.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.INVISIBLE);
        }else {
            balance.setText(resultFromJson.getBalanceResult(output));
            refreshBalance.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        getBalance();
    }

}
