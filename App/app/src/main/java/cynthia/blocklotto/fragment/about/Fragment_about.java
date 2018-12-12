package cynthia.blocklotto.fragment.about;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import cynthia.blocklotto.R;

public class Fragment_about extends Fragment {

    private View view;
    private ListView list;
    private ArrayAdapter<String> adapter;
    private ListView listAdditional;
    private ArrayAdapter<String> adapterAdditional;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_about, container, false);
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);

        setValuesFirstList();
        setValuesSecondList();


        return view;
    }

    private void setValuesFirstList(){
        list = view.findViewById(R.id.list_help);
        adapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1){

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view =super.getView(position, convertView, parent);
                TextView textView=(TextView) view.findViewById(android.R.id.text1);
                textView.setTextColor(ContextCompat.getColor(getContext(), R.color.colorSecondary));
                return view;
            }
        };
        adapter.add("Información de la aplicación");
        adapter.add("Funciones");
        adapter.add("Política de privacidad");
        adapter.add("Código fuente");
        list.setAdapter(adapter);

        controlFirstList();

    }

    private void setValuesSecondList(){
        listAdditional = view.findViewById(R.id.list_additional);
        adapterAdditional = new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1){

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view =super.getView(position, convertView, parent);
                TextView textView=(TextView) view.findViewById(android.R.id.text1);
                textView.setTextColor(ContextCompat.getColor(getContext(), R.color.colorSecondary));
                return view;
            }
        };
        adapterAdditional.add("LottoService");
        adapterAdditional.add("Zxing");
        adapterAdditional.add("android-gif-drawable");
        adapterAdditional.add("Gson");
        listAdditional.setAdapter(adapterAdditional);

        controlSecondList();
    }

    private void controlFirstList(){
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:
                        Intent infoAp = new Intent(getActivity(), Fragment_infoApp.class);
                        getActivity().startActivity(infoAp);
                        break;
                    case 1:
                        Intent functionAp = new Intent(getActivity(), Fragment_functionApp.class);
                        getActivity().startActivity(functionAp);
                        break;
                    case 2:
                        Intent politics = new Intent(getActivity(), Fragment_politics.class);
                        getActivity().startActivity(politics);
                        break;
                    case 3:
                        Uri uri = Uri.parse("https://github.com/CynthiaAG/BlockLotto");
                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        break;
                }
            }
        });
    }
    private void controlSecondList(){
        listAdditional.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:
                        Uri uri0 = Uri.parse("https://github.com/kovutech/BlockLotto");
                        Intent intent0 = new Intent(Intent.ACTION_VIEW, uri0);
                        startActivity(intent0);
                        break;
                    case 1:
                        Uri uri1 = Uri.parse("https://github.com/journeyapps/zxing-android-embedded");
                        Intent intent1 = new Intent(Intent.ACTION_VIEW, uri1);
                        startActivity(intent1);
                        break;
                    case 2:
                        Uri uri2 = Uri.parse("https://github.com/koral--/android-gif-drawable");
                        Intent intent2 = new Intent(Intent.ACTION_VIEW, uri2);
                        startActivity(intent2);
                        break;
                    case 3:
                        Uri uri3 = Uri.parse("https://github.com/google/gson");
                        Intent intent3 = new Intent(Intent.ACTION_VIEW, uri3);
                        startActivity(intent3);
                        break;
                }
            }
        });
    }
}
