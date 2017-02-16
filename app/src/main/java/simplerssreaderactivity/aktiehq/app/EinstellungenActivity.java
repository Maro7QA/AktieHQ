package simplerssreaderactivity.aktiehq.app;

/**
 * Created by hoffmmai on 3/19/2016.
 */

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;


public class EinstellungenActivity extends PreferenceActivity
        implements Preference.OnPreferenceChangeListener {

    @Override

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.preferences);

        // suchen nach preference
        Preference aktienlistePref = findPreference(getString(R.string.preference_aktienliste_key));
        // Verweis speichern in variable
        aktienlistePref.setOnPreferenceChangeListener(this);

        // onPreferenceChange sofort aufrufen mit der in SharedPreferences gespeicherten Aktienliste

        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);

        String gespeicherteAktienliste = sharedPrefs.getString(aktienlistePref.getKey(), "");

        onPreferenceChange(aktienlistePref, gespeicherteAktienliste);



    }


    @Override

    public boolean onPreferenceChange(Preference preference, Object value) {

        preference.setSummary(value.toString());

        return true;

    }

}
