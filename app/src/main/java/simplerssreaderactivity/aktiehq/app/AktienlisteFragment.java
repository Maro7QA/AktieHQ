package simplerssreaderactivity.aktiehq.app;

/**
 * Created by hoffmmai on 2/27/2016.
 */

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;


public class AktienlisteFragment extends Fragment {

    // Schlüssel-Konstante für das Speichern und Auslesen der Finanzdaten

    static final String STATE_DATA = "Finanzdaten";




    // Lesson 15 Tag für das Logging des Fragment-Lifecycle definieren

    private final String LOG_TAG = AktienlisteFragment.class.getSimpleName();


    @Override

    public void onAttach(Activity activity) {

        super.onAttach(activity);

        Log.v(LOG_TAG, "In Callback-Methode: onAttach()");

}



    @Override

    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);

        Log.v(LOG_TAG, "In Callback-Methode: onActivityCreated()");

    }



    @Override

    public void onStart() {

        super.onStart();

        Log.v(LOG_TAG, "In Callback-Methode: onStart()");

    }



    @Override

    public void onResume() {

        super.onResume();

        Log.v(LOG_TAG, "In Callback-Methode: onResume()");

    }



    @Override

    public void onPause() {

        super.onPause();

        Log.v(LOG_TAG, "In Callback-Methode: onPause()");

    }



    @Override

    public void onStop() {

        super.onStop();

        Log.v(LOG_TAG, "In Callback-Methode: onStop()");

    }



    @Override

    public void onDestroyView() {

        super.onDestroyView();

        Log.v(LOG_TAG, "In Callback-Methode: onDestroyView()");

    }



    @Override

    public void onDestroy() {

        super.onDestroy();

        Log.v(LOG_TAG, "In Callback-Methode: onDestroy()");

    }



    @Override

    public void onDetach() {

        super.onDetach();

        Log.v(LOG_TAG, "In Callback-Methode: onDetach()");

    }


// Der ArrayAdapter ist jetzt eine Membervariable der Klasse AktienlisteFragment
    ArrayAdapter<String> mAktienlisteAdapter;
    //SwipeRefresh ist jetzt Membervariable der Klasse AktienlisteFragment
    SwipeRefreshLayout mSwipeRefreshLayout;


    public AktienlisteFragment() {    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // MenÃ¼ bekannt geben, dadurch kann unser Fragment MenÃ¼-Events verarbeiten
        setHasOptionsMenu(true);
        Log.v(LOG_TAG, "In Callback-Methode: onCreate()");
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_aktienlistefragment, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Wir prüfen, ob Menü-Element mit der ID "action_daten_aktualisieren"
        // ausgewählt wurde, holen die Finanzdaten und geben eine Meldung aus

        int id = item.getItemId();

        if (id == R.id.action_daten_aktualisieren) {

            aktualisiereDaten();

            return true;
        }

        return super.onOptionsItemSelected(item);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Log.v(LOG_TAG, "In Callback-Methode: onCreateView()");

        String LOG_TAG = AktienlisteFragment.class.getSimpleName();

        Log.v(LOG_TAG, "verbose     - Meldung");
        Log.d(LOG_TAG, "debug       - Meldung");
        Log.i(LOG_TAG, "information - Meldung");
        Log.w(LOG_TAG, "warning     - Meldung");
        Log.e(LOG_TAG, "error       - Meldung");

        String [] aktienlisteArray = {
                "",
                "",
                "",
                "Placeholder1",
                "Placeholder2",
                "Placeholder3",
                "Placeholder4",
                "Placeholder5",
                "Placeholder6"
        };

        //prüfen ob der vorherige Fragment-Zustand gesichert wurde und lesen die gespeicherten Aktiendaten aus dem Bundle aus
        if (savedInstanceState != null) {

            // Wiederherstellen der Werte des gespeicherten Fragment-Zustands

            aktienlisteArray = savedInstanceState.getStringArray(STATE_DATA);


            Log.v(LOG_TAG, "Zustand des Fragments wieder hergestellt.");

        }


        List<String> aktienListe = new ArrayList<>(Arrays.asList(aktienlisteArray));

        mAktienlisteAdapter =
                new ArrayAdapter<>(
                        getActivity(), // Die aktuelle Umgebung (diese Activity)
                        R.layout.list_item_aktienliste, // ID der XML-Layout Datei
                        R.id.list_item_aktienliste_textview, // ID des TextViews
                        aktienListe); // Beispieldaten in einer ArrayList


        View rootView = inflater.inflate(R.layout.fragment_aktienliste, container, false);


        ListView aktienlisteListView = (ListView) rootView.findViewById(R.id.listview_aktienliste);
        aktienlisteListView.setAdapter(mAktienlisteAdapter);
        aktienlisteListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override

            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                String aktienInfo = (String) adapterView.getItemAtPosition(position);

                // Intent erzeugen und Starten der AktiendetailActivity mit explizitem Intent
                Intent aktiendetailIntent = new Intent(getActivity(), AktiendetailActivity.class);
                aktiendetailIntent.putExtra(Intent.EXTRA_TEXT, aktienInfo);
                startActivity(aktiendetailIntent);


            }

        });

        mSwipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipe_refresh_layout_aktienliste);

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                aktualisiereDaten();

            }

        });


        return rootView;
    }

    @Override

    public void onSaveInstanceState (Bundle savedInstanceState) {

        // Always call the superclass so it can save the view hierarchy state

        super.onSaveInstanceState(savedInstanceState);

        Log.v(LOG_TAG, "In Callback-Methode: onSaveInstanceState()");

        // Speichern der aktualisierten Aktiendaten, wenn bsp. rotieren des Screens
        int anzahlElemente = mAktienlisteAdapter.getCount();

        String [] aktienlisteArray = new String[anzahlElemente];

        for (int i=0; i < anzahlElemente; i++) {

            aktienlisteArray[i] = mAktienlisteAdapter.getItem(i);
        }
        // Werte  von aktualisierten Aktiendaten zugeweisen
        savedInstanceState.putStringArray(STATE_DATA, aktienlisteArray);

    }




    public void aktualisiereDaten() {

        // Erzeugen einer Instanz von HoleDatenTask
        HoleDatenTask holeDatenTask = new HoleDatenTask();


        // Auslesen der ausgewählten Aktienliste aus den SharedPreferences
        SharedPreferences sPrefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String prefAktienlisteKey = getString(R.string.preference_aktienliste_key);
        String prefAktienlisteDefault = getString(R.string.preference_aktienliste_default);
        String aktienliste = sPrefs.getString(prefAktienlisteKey, prefAktienlisteDefault);


        // Auslesen des Anzeige-Modus aus den SharedPreferences
        String prefIndizemodusKey = getString(R.string.preference_indizemodus_key);
        Boolean indizemodus = sPrefs.getBoolean(prefIndizemodusKey, false);



        // Starten des asynchronen Tasks und Übergabe der Aktienliste

        if (indizemodus) {
            String indizeliste = "^GDAXI,^TECDAX,^MDAXI,^SDAXI,^GSPC,^N225,^HSI,XAGUSD=X,XAUUSD=X";
            holeDatenTask.execute(indizeliste);
        }

        else {
            holeDatenTask.execute(aktienliste);
        }


        // Den Benutzer informieren, dass neue Aktiendaten im Hintergrund abgefragt werden

        Toast.makeText(getActivity(), "Aktiendaten werden abgefragt!", Toast.LENGTH_SHORT).show();

    }


    // Innere Klasse HoleDatenTask fÃ¼hrt den asynchronen Task auf eigenem Arbeitsthread aus
    public class HoleDatenTask extends AsyncTask<String, Integer, String[]> {

        private String[] leseXmlAktiendatenAus(String xmlString) {

            Document doc;
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            try {
                DocumentBuilder db = dbf.newDocumentBuilder();
                InputSource is = new InputSource();
                is.setCharacterStream(new StringReader(xmlString));
                doc = db.parse(is);
            } catch (ParserConfigurationException e) {
                Log.e(LOG_TAG,"Error: " + e.getMessage());
                return null;
            } catch (SAXException e) {
                Log.e(LOG_TAG,"Error: " + e.getMessage());
                return null;
            } catch (IOException e) {
                Log.e(LOG_TAG,"Error: " + e.getMessage());
                return null;
            }

            Element xmlAktiendaten = doc.getDocumentElement();
            NodeList aktienListe = xmlAktiendaten.getElementsByTagName("row");

            int anzahlAktien = aktienListe.getLength();
            int anzahlAktienParameter = aktienListe.item(0).getChildNodes().getLength();

            String[] ausgabeArray = new String[anzahlAktien];
            String[][] alleAktienDatenArray = new String[anzahlAktien][anzahlAktienParameter];

            Node aktienParameter;
            String aktienParameterWert;
            for( int i=0; i<anzahlAktien; i++ ) {
                NodeList aktienParameterListe = aktienListe.item(i).getChildNodes();

                for (int j=0; j<anzahlAktienParameter; j++) {
                    aktienParameter = aktienParameterListe.item(j);
                    aktienParameterWert = aktienParameter.getFirstChild().getNodeValue();
                    alleAktienDatenArray[i][j] = aktienParameterWert;
                }

                ausgabeArray[i]  = alleAktienDatenArray[i][0];                // symbol
                ausgabeArray[i] += ": " + alleAktienDatenArray[i][4];         // price
                ausgabeArray[i] += " " + alleAktienDatenArray[i][2];          // currency
                ausgabeArray[i] += " (" + alleAktienDatenArray[i][8] + ")";   // percent
                ausgabeArray[i] += " - [" + alleAktienDatenArray[i][1] + "]"; // name

                Log.v(LOG_TAG,"XML Output:" + ausgabeArray[i]);
            }

            return ausgabeArray;
        }

        private final String LOG_TAG = HoleDatenTask.class.getSimpleName();

        @Override
        protected String[] doInBackground(String... strings) {

            if (strings.length == 0) { // Keine Eingangsparameter erhalten, daher Abbruch
                return null;
            }

            // Exakt so muss die Anfrage-URL an die YQL Platform gesendet werden:
  /*
  https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20csv%20where%20url
  %3D'http%3A%2F%2Fdownload.finance.yahoo.com%2Fd%2Fquotes.csv%3Fs%3D
  BMW.DE%2CDAI.DE%2C%255EGDAXI%26f%3Dsnc4xl1d1t1c1p2ohgv%26e%3D.csv'%20and%20columns%3D'
  symbol%2Cname%2Ccurrency%2Cexchange%2Cprice%2Cdate%2Ctime%2Cchange%2Cpercent%2C
  open%2Chigh%2Clow%2Cvolume'&diagnostics=true";
  */

            // Wir konstruieren die Anfrage-URL für die YQL Platform
            final String URL_PARAMETER = "https://query.yahooapis.com/v1/public/yql";
            final String SELECTOR = "select%20*%20from%20csv%20where%20";
            final String DOWNLOAD_URL = "http://download.finance.yahoo.com/d/quotes.csv";
            final String DIAGNOSTICS = "'&diagnostics=true";
            String symbols = strings[0];

           // String symbols = "BMW.DE,DAI.DE,^GDAXI";
            symbols = symbols.replace("^", "%255E");
            String parameters = "snc4xl1d1t1c1p2ohgv";
            String columns = "symbol,name,currency,exchange,price,date,time," +
                    "change,percent,open,high,low,volume";

            String anfrageString = URL_PARAMETER;
            anfrageString += "?q=" + SELECTOR;
            anfrageString += "url='" + DOWNLOAD_URL;
            anfrageString += "?s=" + symbols;
            anfrageString += "%26f=" + parameters;
            anfrageString += "%26e=.csv'%20and%20columns='" + columns;
            anfrageString += DIAGNOSTICS;

            Log.v(LOG_TAG, "Zusammengesetzter Anfrage-String: " + anfrageString);

            // Die URL-Verbindung und der BufferedReader, werden im finally-Block geschlossen
            HttpURLConnection httpURLConnection = null;
            BufferedReader bufferedReader = null;

            // In diesen String speichern wir die Aktiendaten im XML-Format
            String aktiendatenXmlString = "";

            try {
                URL url = new URL(anfrageString);

                // Aufbau der Verbindung zur YQL Platform
                httpURLConnection = (HttpURLConnection) url.openConnection();

                InputStream inputStream = httpURLConnection.getInputStream();

                if (inputStream == null) { // Keinen Aktiendaten-Stream erhalten, daher Abbruch
                    return null;
                }
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String line;

                while ((line = bufferedReader.readLine()) != null) {
                    aktiendatenXmlString += line + "\n";
                }
                if (aktiendatenXmlString.length() == 0) { // Keine Aktiendaten ausgelesen, Abbruch
                    return null;
                }
                Log.v(LOG_TAG, "Aktiendaten XML-String: " + aktiendatenXmlString);
                publishProgress(1,1);

            } catch (IOException e) { // Beim Holen der Daten trat ein Fehler auf, daher Abbruch
                Log.e(LOG_TAG, "Error ", e);
                return null;
            } finally {
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (final IOException e) {
                        Log.e(LOG_TAG, "Error closing stream", e);
                    }
                }
            }

            // Hier parsen wir später die XML Aktiendaten

            return leseXmlAktiendatenAus(aktiendatenXmlString);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {

            // Auf dem Bildschirm geben wir eine Statusmeldung aus, immer wenn
            // publishProgress(int...) in doInBackground(String...) aufgerufen wird
            Toast.makeText(getActivity(), values[0] + " von " + values[1] + " geladen",
                    Toast.LENGTH_SHORT).show();

        }

        @Override
        protected void onPostExecute(String[] strings) {

            // Wir lÃ¶schen den Inhalt des ArrayAdapters und fÃ¼gen den neuen Inhalt ein
            // Der neue Inhalt ist der RÃ¼ckgabewert von doInBackground(String...) also
            // der StringArray gefÃ¼llt mit Beispieldaten
            if (strings != null) {
                mAktienlisteAdapter.clear();
                for (String aktienString : strings) {
                    mAktienlisteAdapter.add(aktienString);
                }
            }

            // Hintergrundberechnungen sind jetzt beendet, darÃ¼ber informieren wir den Benutzer
            Toast.makeText(getActivity(), "Aktiendaten vollständig geladen!",
                    Toast.LENGTH_SHORT).show();

            // SwipeRefreshLayout-View mitteilen, dass der Refresh-Prozess abgeschlossen ist & neuen Daten in der Aktienliste angezeigt werden können
            mSwipeRefreshLayout.setRefreshing(false);
        }


    }
}