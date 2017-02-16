package uitest;


import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiSelector;
import android.support.test.uiautomator.Until;
import android.test.InstrumentationTestCase;
import android.widget.TextView;

import java.util.ArrayDeque;
import java.util.Arrays;


/**
 * UIAutomator test for login, and after then pair and unpair Fitsmart multiple times.
 *

 *
 * start command: gradlew cATMMD/ gradlew --daemon connectedMicoachMainDebugAndroidTest
 */
public class WeekDays extends InstrumentationTestCase {


    private static final String EMAIL = "sma@gmx.de";
    private static final String PASSWORD = "aaabbb12";
    private static final String TO = "digitalsports.qa@adidas.com";
    private static final String StravaMail = "micoach.testing@hotmail.de";
    private static final String StravaPassword = "23HardWare76";
    private static final String TO1 = "martin.hoffmeister@hotmail.com";
    private static final int min = 60;
    private static final int amountOfWater = 500;

    private static final int MAX_CYCLES = 2;
    private int actualCycleCounter = 0;

    //public  UiObject maxTemp;

   // public String text;

   // public String location;



    private UiDevice uiDevice;
    private ArrayDeque ArrayUtils;
    // private FileLogger logger;


    public void setUp() {
        uiDevice = UiDevice.getInstance(getInstrumentation());
      //  logger = new FileLogger();
      //  logger.writeLogLine(System.currentTimeMillis() + ", " + "Fitsmart pairing UI test started...");
    }

    public int testStart() throws UiObjectNotFoundException {
        startHomeScreen();

        //   signMail();

        StartApp();

        uiDevice.wait(Until.hasObject(By.res("delay")), 2000);
        UiObject TextView = uiDevice.findObject(new UiSelector().className("android.widget.LinearLayout"));
        uiDevice.wait(Until.hasObject(By.clazz(TextView.class).text("delay")), 500); // wait 500ms
        String date = TextView.getChild(new UiSelector().clickable(true).index(2)).getText();
        String[] day = date.split("\\ ");
        System.out.println(Arrays.toString(day));

        uiDevice.wait(Until.hasObject(By.res("delay")), 2000);
        String textTemp = returnText("com.wetter.androidclient:id/txt_max_temperature");
        String[] tempSt= textTemp.split("\\°");
        System.out.println(Arrays.toString(tempSt));
        int[] temp = new int[tempSt.length];
        temp[0] = Integer.parseInt(tempSt[0]);

        String location = returnText("android:id/text1");

        uiDevice.wait(Until.hasObject(By.res("delay")), 1000);

        startHomeScreen();
        StartAppTxtFr();
       // selectShare();


       // UiObject mailText = uiDevice.findObject(new UiSelector().resourceId("com.adidas.micoach:id/share_workouts_image_title"));

        UiObject mailText = uiDevice.findObject(new UiSelector().resourceId("com.ktix007.talk:id/etMain"));

        // mailText.setText(text);
        final int veryLowTemp = 5;

        final String[] weekday = new String [] {"MON","TUE","WED","THU","FRI","SAT","SUN"};
        final String[] actWeekday = new String [] {"Saturday","Sunday","Monday","Tuesday","Wednesday","Thursday","Friday"};
        final int  i =  Arrays.asList(weekday).indexOf(day[0]);


        // final String[] veryhighTempArray = new String[] {"31°","32°","33°","34°","35°","36°","37°","38°","39°","40°","41°","42°","43°","44°","45°","46°","47°","48°","49°","50°"};
       // final String[] highTempArray = new String[] {"21°","22°","23°","24°","25°","26°","27°","28°","29°","30°"};
       // final String[] mildTempArray = new String[] {"11°","12°","13°","14°","15°","16°","17°","18°","19°","20°"};
       // final String[] midTempArray = new String[] {"0°","1°","2°","3°","4°","5°","6°","7°","8°","9°","10°"};
       // final String[] lowTempArray = new String[] {"-1°","-2°","-3°","-4°","-5°","-6°","-7°","-8°","-9°","-10°","-11°","-12°","-13°","-14°","-15°","-16°","-17°","-18°","-19°","-20°","-21°","-22°","-23°","-24°","-25°","-26°","-27°","-28°","-29°","-30°"};

        if ( temp[0] < veryLowTemp ) {
            int water = (int) (1.0 * amountOfWater * (min / 30));
            mailText.setText("Hello, today is " +  actWeekday[i]+ " and the temperature in " + location + " is "  + textTemp + " You need to drink " + water + " ml of water for a " + min + " minute run!");
            uiDevice.wait(Until.hasObject(By.res("delay")), 1000);
        }

        
/**
        if (temp[0] < (veryLowTemp + 10)) {
            int water = (int) (1.0 * amountOfWater * (min / 30));
            mailText.setText("Hello, the temperature in " + location + " is " +  textTemp + " today and you need to drink " + water +" ml of water for a " + min + " minute run!");
            uiDevice.wait(Until.hasObject(By.res("delay")), 1000);
        }

        if ( temp[0] < (veryLowTemp + 10)) {
            int water = (int) (1.5 * amountOfWater * (min / 30));
            mailText.setText("Hello, the temperature in " + location + " is " +  textTemp + " today and you need to drink " + water +" ml of water for a " + min + " minute run!");
            uiDevice.wait(Until.hasObject(By.res("delay")), 1000);
        }

        if ( temp[0] < (veryLowTemp + 10)) {
            int water = (int) (2.0 * amountOfWater * (min / 30));
            mailText.setText( "Hello, the temperature in " + day + location + " is " +  textTemp + " today and you need to drink " + water +" ml of water for a " + min + " minute run!");
            uiDevice.wait(Until.hasObject(By.res("delay")), 1000);
        }

        if ( temp[0] < (veryLowTemp + 25)) {
            int water = (int) (2.5 * amountOfWater * (min / 30));
            mailText.setText("Hello, the temperature in" + location + " is " +  textTemp + " today and you need to drink " + water +" ml of water for a " + min + " minute run! But be careful, it's very dangerous to run in extrem heat!");
            uiDevice.wait(Until.hasObject(By.res("delay")), 1000);
        }


*/


        /** Train and Run if you want to send it out via mail
        uiDevice.wait(Until.hasObject(By.res("delay")), 500);
        uiDevice.click((uiDevice.getDisplayWidth() / 2), (1 * uiDevice.getDisplayHeight() / 3));
        uiDevice.wait(Until.hasObject(By.res("delay")), 500);
        uiDevice.click((uiDevice.getDisplayWidth() / 10), (1 * uiDevice.getDisplayHeight() / 3));
        uiDevice.wait(Until.hasObject(By.res("delay")), 500);
        uiDevice.click((uiDevice.getDisplayWidth() / 2), (22 * uiDevice.getDisplayHeight() / 50));
        uiDevice.wait(Until.hasObject(By.res("delay")), 500);
        uiDevice.click(48*(uiDevice.getDisplayWidth() / 1080), (21 * uiDevice.getDisplayHeight() / 50));
        uiDevice.wait(Until.hasObject(By.res("delay")), 500);
        uiDevice.click((uiDevice.getDisplayWidth() / 2), (22 * uiDevice.getDisplayHeight() / 50));
        */

        UiObject play = uiDevice.findObject(new UiSelector().resourceId("com.ktix007.talk:id/fab"));

        play.click();

        uiDevice.wait(Until.hasObject(By.res("delay")), 20000);

       // sendMail();


        return veryLowTemp;
    }

    private void startHomeScreen() {
        uiDevice.pressHome();
        uiDevice.wait(Until.hasObject(By.pkg(getHomeScreenPackage()).depth(0)), 5000);
    }

    private String getHomeScreenPackage() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        ResolveInfo defaultLauncher = getInstrumentation().getContext().getPackageManager().resolveActivity(intent,
                PackageManager.MATCH_DEFAULT_ONLY);
        return defaultLauncher.activityInfo.packageName;
    }

    private void StartApp() throws UiObjectNotFoundException {
        uiDevice.wait(Until.hasObject(By.res("Unkown")), 2000);

        uiDevice.wait(Until.hasObject(By.clazz(TextView.class).text("wetter.com")), 500);

        UiObject wetter  = uiDevice.findObject(new UiSelector().className(TextView.class).text("wetter.com"));
        wetter.click();

        uiDevice.wait(Until.hasObject(By.res("Unkown")), 1000);

    }

    private void StartAppTxtFr() throws UiObjectNotFoundException {
        uiDevice.wait(Until.hasObject(By.res("Unkown")), 2000);

        uiDevice.wait(Until.hasObject(By.clazz(TextView.class).text("Talk FREE")), 500);

        UiObject wetter  = uiDevice.findObject(new UiSelector().className(TextView.class).text("Talk FREE"));
        wetter.click();

        uiDevice.wait(Until.hasObject(By.res("Unkown")), 1000);

    }

    private void StartAppTaR() throws UiObjectNotFoundException {
        uiDevice.wait(Until.hasObject(By.res("Unkown")), 2000);

        uiDevice.wait(Until.hasObject(By.clazz(TextView.class).text("train & run")), 500);

        UiObject wetter  = uiDevice.findObject(new UiSelector().className(TextView.class).text("train & run"));
        wetter.click();

        uiDevice.wait(Until.hasObject(By.res("Unkown")), 1000);


    }

    private void StartAppAktieHQ() throws UiObjectNotFoundException {
        uiDevice.wait(Until.hasObject(By.res("Unkown")), 2000);

        uiDevice.wait(Until.hasObject(By.clazz(TextView.class).text("AktieHQ")), 500);

        UiObject wetter  = uiDevice.findObject(new UiSelector().className(TextView.class).text("AktieHQ"));
        wetter.click();

        uiDevice.wait(Until.hasObject(By.res("Unkown")), 1000);


    }

    private void swipeToRunScreen() throws UiObjectNotFoundException {

        uiDevice.wait(Until.hasObject(By.res("Unkown")), 2000);


        UiObject Train = uiDevice.findObject(new UiSelector().className(TextView.class).text("Want to train smarter? Create a personalized plan."));


        if (Train.exists()) {
            uiDevice.swipe(1* uiDevice.getDisplayWidth()/ 12, uiDevice.getDisplayHeight() / 3, 11 * uiDevice.getDisplayWidth()/ 12, uiDevice.getDisplayHeight() / 3, 40);
        }

        uiDevice.wait(Until.hasObject(By.res("Unkown")), 2000);
    }

    private void launchUIAutomation() throws UiObjectNotFoundException {
        Intent micoach = new Intent();
        micoach.setAction(Intent.ACTION_MAIN);
        micoach.addCategory(Intent.CATEGORY_LAUNCHER);
        micoach.setComponent(new ComponentName("com.adidas.micoach.testing", "com.adidas.micoach.ui.startup.SplashActivity"));
        micoach.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        getInstrumentation().getContext().startActivity(micoach);
        uiDevice.wait(Until.hasObject(By.clazz(TextView.class).text("English (UK)")), 10000);
        uiDevice.waitForIdle(1000);

        // sign with Facebook
        // uiDevice.wait(Until.hasObject(By.clazz(TextView.class).text("Sign in with FACEBOOK")), 10000);

        //UiObject SignFB = uiDevice.findObject(new UiSelector().className(TextView.class).text("Sign in with FACEBOOK"));
        //SignFB.click();


    }

    private void selectListItem() throws UiObjectNotFoundException {

        uiDevice.wait(Until.hasObject(By.res("delay")), 1000);

        UiObject listItem = uiDevice.findObject(new UiSelector().resourceId("simplerssreaderactivity.aktiehq.app:id/list_item_aktienliste_textview"));

        listItem.click();

        uiDevice.wait(Until.hasObject(By.res("delay")), 1000);
        uiDevice.waitForIdle(1000);


    }

    public void readTemp() throws UiObjectNotFoundException {
        uiDevice.wait(Until.hasObject(By.res("Delay")), 2000);

        UiObject maxTemp = uiDevice.findObject(new UiSelector().resourceId("com.wetter.androidclient:id/txt_max_temperature"));

        final String text;

        text = maxTemp.getText();

        uiDevice.wait(Until.hasObject(By.res("Unkown")), 1000);

        UiObject locationText = uiDevice.findObject(new UiSelector().resourceId("android:id/text1"));

        final String location;

        location = locationText.getText();




    }

    public String returnText(String myinput) throws UiObjectNotFoundException {

        uiDevice.wait(Until.hasObject(By.res("Delay")), 1000);

        return  uiDevice.findObject(new UiSelector().resourceId(myinput)).getText();
    }
       // launch other app

    private void selectMailIcon() throws UiObjectNotFoundException {
        uiDevice.wait(Until.hasObject(By.res("Unkown")), 2000);

        uiDevice.wait(Until.hasObject(By.clazz(TextView.class).text("Apps")), 500);

        UiObject Mail  = uiDevice.findObject(new UiSelector().className(TextView.class).text("Apps"));
        Mail.click();

        uiDevice.wait(Until.hasObject(By.res("Unkown")), 1000);

        uiDevice.swipe(uiDevice.getDisplayWidth() / 2, uiDevice.getDisplayHeight() / 2, uiDevice.getDisplayWidth() / 4, uiDevice.getDisplayHeight() / 2, 500);

        uiDevice.swipe(uiDevice.getDisplayWidth() / 2, uiDevice.getDisplayHeight() / 2, uiDevice.getDisplayWidth() / 4, uiDevice.getDisplayHeight() / 2, 500);


    }



    // launch the app
    private void launchApp() throws UiObjectNotFoundException {
        Intent AktieHQ = new Intent();
        AktieHQ.setAction(Intent.ACTION_MAIN);
        AktieHQ.addCategory(Intent.CATEGORY_LAUNCHER);
        AktieHQ.setComponent(new ComponentName("simplerssreaderactivity.aktiehq.app", "simplerssreaderactivity.aktiehq.app.MainActivity"));
        AktieHQ.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        getInstrumentation().getContext().startActivity(AktieHQ);
        uiDevice.wait(Until.hasObject(By.clazz(TextView.class).text("English (UK)")), 10000);
        uiDevice.waitForIdle(1000);

    }

    private void  selectMenu () throws UiObjectNotFoundException {

        // select menu
        uiDevice.click(10 * (uiDevice.getDisplayWidth() / 11), uiDevice.getDisplayHeight() / 9);

        uiDevice.wait(Until.hasObject(By.res("Unkown")), 2000);

        uiDevice.wait(Until.hasObject(By.clazz(TextView.class).text("Settings")), 500);

        UiObject Settings  = uiDevice.findObject(new UiSelector().className(TextView.class).text("Settings"));
        Settings.click();

        uiDevice.wait(Until.hasObject(By.res("Unkown")), 1000);

        UiObject tickBox = uiDevice.findObject(new UiSelector().resourceId("android:id/checkbox"));
        tickBox.click();

        uiDevice.wait(Until.hasObject(By.res("Unkown")), 500);

        uiDevice.pressBack();

        uiDevice.wait(Until.hasObject(By.res("Unkown")), 500);


    }




    public void clickListViewItem(int index) throws UiObjectNotFoundException {
        // find list view
        UiObject ListView = uiDevice.findObject(new UiSelector().resourceId("simplerssreaderactivity.aktiehq.app:id/listview_aktienliste"));
        // get index of ChildCount and click on specific Child of list if index is smaller than total list view index
        if(index <= ListView.getChildCount()){
            ListView.getChild(new UiSelector().clickable(true).index(index)).click();
            uiDevice.wait(Until.hasObject(By.res("Unkown")), 1500);

            // send out mail section
            startSharing();
            sendMail();
            // return to main menu
            uiDevice.pressBack();
            uiDevice.wait(Until.hasObject(By.res("Unkown")), 500);


        }
    }


    // click on ActivityType button com.adidas.micoach.beta:id/action_share_goal

    public void startSharing() throws UiObjectNotFoundException {

        // select predefined GMail icon

        uiDevice.wait(Until.hasObject(By.clazz(TextView.class).text("unkown")), 1000);
        // click sharing icon
        UiObject sharing = uiDevice.findObject(new UiSelector().resourceId("simplerssreaderactivity.aktiehq.app:id/default_activity_button"));
        sharing.click();

        uiDevice.wait(Until.hasObject(By.res("unkown")), 5000);




    }



    //Set mail address to defined mail address and send it out
    public void selectShare() throws UiObjectNotFoundException {

        // select receiver

        uiDevice.wait(Until.hasObject(By.clazz(TextView.class).text("delay")), 2000);

        UiObject burgerMenu = uiDevice.findObject(new UiSelector().className("android.widget.ImageButton"));
        burgerMenu.click();
        uiDevice.wait(Until.hasObject(By.clazz(TextView.class).text("delay")), 1000);

        UiObject actTrack  = uiDevice.findObject(new UiSelector().className(TextView.class).text("Activity Tracking"));
        actTrack.click();
        uiDevice.wait(Until.hasObject(By.clazz(TextView.class).text("delay")), 1000);


        // click send
        UiObject share = uiDevice.findObject(new UiSelector().resourceId("com.adidas.micoach:id/action_share_goal"));
        share.click();

        uiDevice.waitForIdle(1000);
        uiDevice.wait(Until.hasObject(By.clazz(TextView.class).text("delay")), 1000);


    }

    public void sendMail() throws UiObjectNotFoundException {

        // select receiver

        uiDevice.wait(Until.hasObject(By.clazz(TextView.class).text("delay")), 2000);

        UiObject share = uiDevice.findObject(new UiSelector().resourceId("com.adidas.micoach:id/improved_sharing_tab_submit"));
        share.click();
        uiDevice.wait(Until.hasObject(By.clazz(TextView.class).text("delay")), 1000);

        UiObject gmail  = uiDevice.findObject(new UiSelector().className(TextView.class).text("Gmail"));
        gmail.click();
        uiDevice.wait(Until.hasObject(By.clazz(TextView.class).text("delay")), 1000);

        UiObject to = uiDevice.findObject(new UiSelector().resourceId("com.google.android.gm:id/to"));
        to.setText(TO1);
        uiDevice.waitForIdle(1000);
        uiDevice.wait(Until.hasObject(By.clazz(TextView.class).text("delay")), 2000);
        // click send
        UiObject send = uiDevice.findObject(new UiSelector().resourceId("com.google.android.gm:id/send"));
        send.click();

        uiDevice.waitForIdle(1000);
        uiDevice.wait(Until.hasObject(By.clazz(TextView.class).text("delay")), 2000);

        UiObject backButton = uiDevice.findObject(new UiSelector().className("android.widget.ImageButton"));
        backButton.click();
        uiDevice.wait(Until.hasObject(By.clazz(TextView.class).text("delay")), 1000);

        UiObject burgerMenu = uiDevice.findObject(new UiSelector().className("android.widget.ImageButton"));
        burgerMenu.click();
        uiDevice.wait(Until.hasObject(By.clazz(TextView.class).text("delay")), 1000);

        UiObject run  = uiDevice.findObject(new UiSelector().className(TextView.class).text("Run"));
        run.click();
        uiDevice.wait(Until.hasObject(By.clazz(TextView.class).text("delay")), 1000);


    }







    public void tearDown() {
      //  logger.writeLogLine(System.currentTimeMillis() + ", " + "Training plan automation done.");
       // logger.close();

    }





}

