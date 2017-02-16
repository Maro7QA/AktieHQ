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


/**
 *
 * connectedDebugAndroidTest
 * Created by hoffmmai on 06/28/2016.
 */
public class LaunchApp extends InstrumentationTestCase {

    private static UiDevice uiDevice;
    private static final int MAX_CYCLES = 2;
    private int actualCycleCounter = 0;
    private static final String TO1 = "martin.hoffmeister@hotmail.com";

    private static final String TO3 = "nadja.hellmuth@web.de";


    public void setUp() {
        uiDevice = UiDevice.getInstance(getInstrumentation());

    }


    public void testStart() throws UiObjectNotFoundException {
        startHomeScreen();
        selectMailIcon();
        launchApp();


        // iterate through list of home screen ListView
        UiObject ListView = uiDevice.findObject(new UiSelector().resourceId("simplerssreaderactivity.aktiehq.app:id/listview_aktienliste"));
        for (int i = 0; i < ListView.getChildCount() && i < 3; i++) {
            clickListViewItem(i);

        }
        // change settings loop

        for (; actualCycleCounter < MAX_CYCLES;) {
            actualCycleCounter++;

            selectMenu();

        }

    }

    // select Home Screen
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

        uiDevice.wait(Until.hasObject(By.res("unkown")), 2000);




    }



    //Set mail address to defined mail address and send it out
    public void sendMail() throws UiObjectNotFoundException {

        // select receiver
        UiObject to = uiDevice.findObject(new UiSelector().resourceId("com.samsung.android.email.provider:id/recipient_to_edit_addresstext"));
        to.setText(TO1);
        uiDevice.waitForIdle(1000);
        uiDevice.wait(Until.hasObject(By.clazz(TextView.class).text("unkown")), 2000);
        // click send
        UiObject send = uiDevice.findObject(new UiSelector().resourceId("com.samsung.android.email.provider:id/menu_composer_send"));
        send.click();

        uiDevice.waitForIdle(1000);
        uiDevice.wait(Until.hasObject(By.clazz(TextView.class).text("unkown")), 2000);




    }


}