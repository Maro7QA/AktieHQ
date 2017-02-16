package simplerssreaderactivity.aktiehq.app;

/**
 * Created by hoffmmai on 3/6/2016.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class AktiendetailFragment extends Fragment {
    public AktiendetailFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_aktiendetail, container, false);

        // Die AktiendetailActivity wurde über einen Intent aufgerufen
        // Wir lesen aus dem empfangenen Intent die übermittelten Daten aus
        // Variable vom Datentyp Intent und empfangene Intent-Objekt zu
        // prüfen ob der Intent auch Daten enthält
        // lesen übermittelte Finanzinformation aus dem Intent-Objekt und speichern in dem String aktienInfo

        Intent empfangenerIntent = getActivity().getIntent();
        if (empfangenerIntent != null && empfangenerIntent.hasExtra(Intent.EXTRA_TEXT)) {
            String aktienInfo = empfangenerIntent.getStringExtra(Intent.EXTRA_TEXT);

            //suchen nach dem TextView unseres Fragments und speichern Aktieninformation darin
            ((TextView) rootView.findViewById(R.id.aktiendetail_text))
                    .setText(aktienInfo);
        }
        return rootView;
    }

}
