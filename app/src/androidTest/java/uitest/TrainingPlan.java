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
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;




/**gradlew --daemon connectedMicoachMainDebugAndroidTest
 * Created by hoffmmai on 04/19/2016.
 */



public class TrainingPlan extends InstrumentationTestCase {

    private static final int MAX_CYCLES = 119; //added by MH
    private static final String EMAIL = "smartw2@gmx.de";
    private static final String PASSWORD = "aaabbb12";
    private static final String TO = "digitalsports.qa@adidas.com";

    private int actualCycleCounter = 0;  //added by MH


    private UiDevice uiDevice;
  //  private FileLogger logger;


    public void setUp() {
        uiDevice = UiDevice.getInstance(getInstrumentation());
      //  logger = new FileLogger();
       // logger.writeLogLine(System.currentTimeMillis() + ", " + "Fitsmart pairing UI test started...");
    }

    public void testStart() throws UiObjectNotFoundException {
        startHomeScreen();
      //  launchMicoach();
        //signInMail();
        StartApp();

        /**

        swipeToChooseTP();
        selectTrainingPlanActivity();
        selectRunningDistance();
        selectRunningTime();
        // selectAdd();
        selectAddPlans();
        selectScheduleYourPlan();
        selectCalenderNotNow();
         */

        selectRunMenu();


        swipeToChooseTP();


        for (; actualCycleCounter < MAX_CYCLES;) {
            actualCycleCounter++;


            UiObject AllWo  = uiDevice.findObject(new UiSelector().className(TextView.class).text("ALL WORKOUTS"));



            if (AllWo.exists()) {
                pressStart();

            } else {
                swipeToChooseTP();
                pressStart();
            }

            swipeToPause();
            pressEndWorkout();
            WorkoutDone();
            selectRunMenu();
        }




        selectTrainMenu();
        startSharing();
        selectGmail();
        sendMail();
        selectEndPlan();
        selectConfirmEndPlan();



    }

    private void startHomeScreen() {
        uiDevice.pressHome();
        uiDevice.wait(Until.hasObject(By.pkg(getHomeScreenPackage()).depth(0)), 5000);
    }

    private void StartApp() throws UiObjectNotFoundException {
        uiDevice.wait(Until.hasObject(By.res("Unkown")), 2000);

        uiDevice.wait(Until.hasObject(By.clazz(TextView.class).text("Apps")), 500);

        UiObject AppStart  = uiDevice.findObject(new UiSelector().className(TextView.class).text("train & run"));
        AppStart.click();

        uiDevice.wait(Until.hasObject(By.res("Unkown")), 20000);


    }

    private String getHomeScreenPackage() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        ResolveInfo defaultLauncher = getInstrumentation().getContext().getPackageManager().resolveActivity(intent,
                PackageManager.MATCH_DEFAULT_ONLY);
        return defaultLauncher.activityInfo.packageName;
    }

    private void launchMicoach() throws UiObjectNotFoundException {
        Intent micoach = new Intent();
        micoach.setAction(Intent.ACTION_MAIN);
        micoach.addCategory(Intent.CATEGORY_LAUNCHER);
        micoach.setComponent(new ComponentName("com.adidas.micoach.testing", "com.adidas.micoach.ui.startup.SplashActivity"));
        micoach.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        getInstrumentation().getContext().startActivity(micoach);
        uiDevice.wait(Until.hasObject(By.clazz(TextView.class).text("English (UK)")), 200);
        uiDevice.waitForIdle(1000);

        // sign with Facebook
        //  uiDevice.wait(Until.hasObject(By.clazz(TextView.class).text("Sign in with FACEBOOK")), 10000);

//        UiObject SignFB = uiDevice.findObject(new UiSelector().className(TextView.class).text("Sign in with FACEBOOK"));
        //      SignFB.click();

        uiDevice.wait(Until.hasObject(By.clazz(TextView.class).text("not_existing_object")), 20000);



    }



    private void signInMail () throws UiObjectNotFoundException {

        // click sign in
        uiDevice.wait(Until.hasObject(By.clazz(TextView.class).text("not_existing_object")), 2000);


        uiDevice.wait(Until.hasObject(By.clazz(TextView.class).text("Sign in with EMAIL")), 10000);

        UiObject Sign = uiDevice.findObject(new UiSelector().className(TextView.class).text("Sign in with EMAIL"));
        Sign.click();


        uiDevice.wait(Until.hasObject(By.clazz(TextView.class).text("not_existing_object")), 2000);

        // select mail object
        uiDevice.wait(Until.hasObject(By.clazz(TextView.class).text("Your email")), 2000);
        UiObject mail = uiDevice.findObject(new UiSelector().className(EditText.class).text("Your email"));
        mail.setText(EMAIL);

        uiDevice.wait(Until.hasObject(By.clazz(TextView.class).text("not_existing_object")), 1000);



        // select password object
        UiObject password = uiDevice.findObject(new UiSelector().resourceId("com.adidas.micoach.testing:id/signin_password"));
        password.setText(PASSWORD);
        uiDevice.waitForIdle(1000);
        uiDevice.wait(Until.hasObject(By.clazz(TextView.class).text("not_existing_object")), 1000);


        uiDevice.wait(Until.hasObject(By.clazz(TextView.class).text("not_existing_object")), 2000);

        uiDevice.wait(Until.hasObject(By.clazz(TextView.class).text("DONE")), 2000);
        UiObject done = uiDevice.findObject(new UiSelector().className(TextView.class).text("DONE"));
        done.click();

        uiDevice.wait(Until.hasObject(By.clazz(TextView.class).text("not_existing_object")), 10000);

    }


    // go to device section
    // select device

    private void swipeToChooseTP() throws UiObjectNotFoundException {
        uiDevice.wait(Until.hasObject(By.res("com.adidas.micoach:id/pair_button")), 3000);

        UiObject Run = uiDevice.findObject(new UiSelector().className(TextView.class).text("Get going! Track your own workout"));

        if (Run.exists()) {

            uiDevice.swipe(11 * uiDevice.getDisplayWidth() / 12, uiDevice.getDisplayHeight() / 3, uiDevice.getDisplayWidth() / 12, uiDevice.getDisplayHeight() / 3, 40);
        }


        uiDevice.wait(Until.hasObject(By.res("com.adidas.micoach:id/pair_button")), 1000);
        uiDevice.waitForIdle(1000);
    }


    // changed by MH inserted private void to check TrainingPlan access

    // choose plan from device list
    private void selectTrainMenu() throws UiObjectNotFoundException {

        uiDevice.swipe(0, uiDevice.getDisplayHeight() / 2, uiDevice.getDisplayWidth(), uiDevice.getDisplayHeight() / 2, 40);
        uiDevice.wait(Until.hasObject(By.clazz(TextView.class).text("Train")), 1000);

        UiObject Train = uiDevice.findObject(new UiSelector().className(TextView.class).text("Train"));
        Train.click();

        uiDevice.wait(Until.hasObject(By.res("com.adidas.micoach:id/pair_button")), 1000);
        uiDevice.waitForIdle(1000);

        // uiDevice.swipe(uiDevice.getDisplayWidth(), uiDevice.getDisplayHeight() / 2, uiDevice.getDisplayWidth() / 2, uiDevice.getDisplayHeight() / 2, 40);

        // uiDevice.wait(Until.hasObject(By.res("com.adidas.micoach:id/pair_button")), 3000);
        uiDevice.waitForIdle(1000);
    }

    // go back to home screen

    private void selectRunMenu() throws UiObjectNotFoundException {

        uiDevice.wait(Until.hasObject(By.res("com.adidas.micoach:id/pair_button")), 1000);

        uiDevice.swipe(0, uiDevice.getDisplayHeight() / 2, uiDevice.getDisplayWidth(), uiDevice.getDisplayHeight() / 2, 40);
        uiDevice.wait(Until.hasObject(By.clazz(TextView.class).text("Run")), 1000);


        //UiObject Run = uiDevice.findObject(new UiSelector().className(TextView.class).text("Run"));
        //Run.click();
        uiDevice.click((uiDevice.getDisplayWidth() / 2), ( uiDevice.getDisplayHeight() / 3));

      //  UiObject run = uiDevice.findObject(new UiSelector().resourceId("com.adidas.micoach.testing:id/tvNavBarItem"));
      //  run.click();

        uiDevice.wait(Until.hasObject(By.res("com.adidas.micoach:id/pair_button")), 1000);
        uiDevice.waitForIdle(1000);

    }




    // press Run
    private void pressStart() throws UiObjectNotFoundException {
        // press Run button
        uiDevice.wait(Until.hasObject(By.res("com.adidas.micoach:id/pair_button")), 20000);


        uiDevice.wait(Until.hasObject(By.clazz(TextView.class).text("DISMISS")), 1000);

        UiObject dismiss = uiDevice.findObject(new UiSelector().className(TextView.class).text("DISMISS"));

        if (dismiss.exists() && dismiss.isClickable()) {
            //logger.writeLogLine(System.currentTimeMillis() + ", " + "(/) Fitsmart reached goal");
            dismiss.click();
            uiDevice.wait(Until.hasObject(By.res("com.adidas.micoach:id/pair_button")), 1000);
            uiDevice.click((uiDevice.getDisplayWidth() / 2), (9 * uiDevice.getDisplayHeight() / 10));
            uiDevice.wait(Until.hasObject(By.res("com.adidas.micoach:id/pair_button")), 1000);
            uiDevice.waitForIdle(1000);
        }

        uiDevice.wait(Until.hasObject(By.res("com.adidas.micoach:id/pair_button")), 1000);
        uiDevice.click((uiDevice.getDisplayWidth() / 2), (9 * uiDevice.getDisplayHeight() / 10));

        uiDevice.wait(Until.hasObject(By.res("com.adidas.micoach:id/pair_button")), 2000);

        uiDevice.wait(Until.hasObject(By.clazz(TextView.class).text("OK")), 1000);

        UiObject OK = uiDevice.findObject(new UiSelector().className(TextView.class).text("OK"));
        if (OK.exists() && OK.isClickable()) {

            OK.click();
            uiDevice.wait(Until.hasObject(By.res("com.adidas.micoach:id/pair_button")), 1000);

        }


        uiDevice.wait(Until.hasObject(By.res("com.adidas.micoach:id/pair_button")), 1000);

        uiDevice.wait(Until.hasObject(By.res("com.adidas.micoach:id/pair_button")), 1000);

        uiDevice.wait(Until.hasObject(By.clazz(Button.class).text("Allow")), 1000);

        UiObject allow = uiDevice.findObject(new UiSelector().className(Button.class).text("Allow"));

        if (allow.exists() && allow.isClickable()) {
            allow.click();
            uiDevice.wait(Until.hasObject(By.res("com.adidas.micoach:id/pair_button")), 1000);

        }


        /**
         // press "GO ANYWAY" Button

         uiDevice.wait(Until.hasObject(By.clazz(TextView.class).text("GO")), 1000);

         UiObject go = uiDevice.findObject(new UiSelector().className(TextView.class).text("GO"));


         if (go.exists() && go.isClickable()) {

         go.click();
         uiDevice.waitForIdle(1000);

         }

         else {
         // go anyway
         uiDevice.click((uiDevice.getDisplayWidth() / 2), (8 * uiDevice.getDisplayHeight() / 10));
         }

         uiDevice.wait(Until.hasObject(By.res("com.adidas.micoach:id/pair_button")), 100);

         uiDevice.waitForIdle(1000);

         */
    }





    // swipe StartButton

    private void swipeToPause() throws UiObjectNotFoundException {


        uiDevice.waitForIdle(10000);

        uiDevice.wait(Until.hasObject(By.clazz(TextView.class).text("not_existing_object")), 20000); // Workout length

        UiObject ExitMap = uiDevice.findObject(new UiSelector().resourceId("com.adidas.micoach.testing:id/in_workout_map_exit_button"));

        if (ExitMap.exists() && ExitMap.isClickable()) {
            ExitMap.click();
            uiDevice.wait(Until.hasObject(By.res("com.adidas.micoach:id/pair_button")), 3000);
        }


        uiDevice.swipe(0, 10 * (uiDevice.getDisplayHeight() / 11), uiDevice.getDisplayWidth(), 10 * (uiDevice.getDisplayHeight() / 11), 100);
        uiDevice.waitForIdle(10000);
        uiDevice.wait(Until.hasObject(By.clazz(TextView.class).text("not_existing_object")), 10000);
    }



    private void pressEndWorkout() throws UiObjectNotFoundException {

        uiDevice.waitForIdle(10000);

        uiDevice.wait(Until.hasObject(By.clazz(TextView.class).text("not_existing_object")), 1000);

        // uiDevice.wait(Until.hasObject(By.clazz(TextView.class).text("END")), 2000);

        //UiObject End = uiDevice.findObject(new UiSelector().className(TextView.class).text("END"));
        //End.click();

        uiDevice.click((uiDevice.getDisplayWidth() / 4), 96 * (uiDevice.getDisplayHeight() / 100));

        uiDevice.waitForIdle(1000);
        uiDevice.wait(Until.hasObject(By.clazz(TextView.class).text("not_existing_object")), 5000);
    }





    private void WorkoutDone() throws UiObjectNotFoundException {


        // confirm Workout
        uiDevice.wait(Until.hasObject(By.res("com.adidas.micoach:id/pair_button")), 2000);

        uiDevice.click(10 * (uiDevice.getDisplayWidth() / 11), uiDevice.getDisplayHeight() / 9);

        uiDevice.wait(Until.hasObject(By.res("com.adidas.micoach:id/pair_button")), 2000);

        // go back to previous screen
        uiDevice.click((uiDevice.getDisplayWidth() / 10), (uiDevice.getDisplayHeight() / 11));
        uiDevice.wait(Until.hasObject(By.res("com.adidas.micoach:id/pair_button")), 2000);


    }



    //To see graphs in landscape please enable screen rotation in phone settings.


    // confirm end plan
    private void selectEndWO() throws UiObjectNotFoundException {

        uiDevice.wait(Until.hasObject(By.clazz(Button.class).text("End")), 2000);

        UiObject End = uiDevice.findObject(new UiSelector().className(Button.class).text("End"));
        if (End.exists() && End.isClickable()) {
            //logger.writeLogLine(System.currentTimeMillis() + ", " + "(/) Workout end successful");
            End.click();
            uiDevice.wait(Until.hasObject(By.res("com.adidas.micoach:id/pair_button")), 2000);
            uiDevice.waitForIdle(1000);

        } else {
            uiDevice.click(9 * (uiDevice.getDisplayWidth() / 11), 13 * (uiDevice.getDisplayHeight() / 14));
            uiDevice.waitForIdle(20000);
        }
    }









// click on ActivityType button com.adidas.micoach.beta:id/action_share_goal

    private void startSharing() throws UiObjectNotFoundException {
        uiDevice.wait(Until.hasObject(By.clazz(TextView.class).text("not_existing_object")), 5000);

        UiObject goal_sharing = uiDevice.findObject(new UiSelector().resourceId("com.adidas.micoach.testing:id/action_share_goal"));
        goal_sharing.click();

        uiDevice.wait(Until.hasObject(By.res("com.adidas.micoach:id/pair_button")), 1000);

        uiDevice.wait(Until.hasObject(By.clazz(Button.class).text("Allow")), 1000);

        UiObject allow = uiDevice.findObject(new UiSelector().className(Button.class).text("Allow"));

        if (allow.exists() && allow.isClickable()) {
            allow.click();
            uiDevice.wait(Until.hasObject(By.res("com.adidas.micoach:id/pair_button")), 1000);

        }

        uiDevice.waitForIdle(1000);
        uiDevice.wait(Until.hasObject(By.clazz(TextView.class).text("not_existing_object")), 2000);
        UiObject share = uiDevice.findObject(new UiSelector().className(TextView.class).text("SHARE"));
        share.click();

        uiDevice.wait(Until.hasObject(By.clazz(TextView.class).text("not_existing_object")), 2000);


    }

//Select Mail account. Test phone has a GMAIL digital sports test account

    private void selectGmail() throws UiObjectNotFoundException{

        uiDevice.swipe((3 * uiDevice.getDisplayWidth())/4, 2 * uiDevice.getDisplayHeight() / 3, (3 * uiDevice.getDisplayWidth())/4, uiDevice.getDisplayHeight() / 10, 40);

        uiDevice.wait(Until.hasObject(By.clazz(TextView.class).text("not_existing_object")), 4000);

        UiObject Gmail = uiDevice.findObject(new UiSelector().className(TextView.class).text("Gmail"));

        Gmail.click();

        uiDevice.waitForIdle(1000);
        uiDevice.wait(Until.hasObject(By.clazz(TextView.class).text("not_existing_object")), 1000);
    }

    //Set mail address to Martin Hoffmeister and send it out
    private void sendMail() throws UiObjectNotFoundException {
        UiObject to = uiDevice.findObject(new UiSelector().resourceId("com.google.android.gm:id/to"));
        to.setText(TO);
        uiDevice.waitForIdle(1000);
        uiDevice.wait(Until.hasObject(By.clazz(TextView.class).text("not_existing_object")), 1000);

        UiObject send = uiDevice.findObject(new UiSelector().resourceId("com.google.android.gm:id/send"));
        send.click();
        //logger.writeLogLine(System.currentTimeMillis() + " ActivityType done");
        uiDevice.waitForIdle(1000);
        uiDevice.wait(Until.hasObject(By.clazz(TextView.class).text("not_existing_object")), 1000);

        uiDevice.click((uiDevice.getDisplayWidth() / 10), (uiDevice.getDisplayHeight() / 11));

    }

    // choose sports
    private void selectTrainingPlanActivity() throws UiObjectNotFoundException {

        // press GO to open Training Plan chooser

        uiDevice.wait(Until.hasObject(By.res("com.adidas.micoach:id/pair_button")), 1000);
        uiDevice.click((uiDevice.getDisplayWidth() / 2), (8 * uiDevice.getDisplayHeight() / 10));
        uiDevice.wait(Until.hasObject(By.res("com.adidas.micoach:id/pair_button")), 10000);

        uiDevice.wait(Until.hasObject(By.clazz(TextView.class).text("RUNNING")), 1000);

        UiObject Running = uiDevice.findObject(new UiSelector().className(TextView.class).text("RUNNING"));
        Running.click();

        uiDevice.wait(Until.hasObject(By.res("com.adidas.micoach:id/pair_button")), 2000);
        uiDevice.waitForIdle(1000);
    }

    // choose running distance
    private void selectRunningDistance() throws UiObjectNotFoundException {

        uiDevice.wait(Until.hasObject(By.clazz(TextView.class).text("5K")), 1000);

        UiObject fiveK = uiDevice.findObject(new UiSelector().className(TextView.class).text("5K"));
        fiveK.click();

        uiDevice.wait(Until.hasObject(By.res("com.adidas.micoach:id/pair_button")), 2000);
        uiDevice.waitForIdle(1000);
    }

    // choose running Time
    private void selectRunningTime() throws UiObjectNotFoundException {

        uiDevice.wait(Until.hasObject(By.clazz(TextView.class).text("Next")), 1000);

        UiObject Next = uiDevice.findObject(new UiSelector().className(TextView.class).text("Next"));
        Next.click();

        uiDevice.wait(Until.hasObject(By.res("com.adidas.micoach:id/pair_button")), 2000);
        uiDevice.waitForIdle(1000);
    }

    // select add plans
    private void selectAddPlans() throws UiObjectNotFoundException {

        uiDevice.wait(Until.hasObject(By.res("com.adidas.micoach:id/pair_button")), 4000);

        //  com.adidas.micoach.testing:id/planChooser_expandAddPlanBtn

        uiDevice.wait(Until.hasObject(By.clazz(TextView.class).text("Add plan")), 2000);

        UiObject AddPlan = uiDevice.findObject(new UiSelector().className(TextView.class).text("Add plan"));
        AddPlan.click();

        //ADD BOTH PLANS

        uiDevice.wait(Until.hasObject(By.res("com.adidas.micoach:id/pair_button")), 1000);
        uiDevice.waitForIdle(1000);
    }

    private void selectBothPlans() throws UiObjectNotFoundException {

        uiDevice.wait(Until.hasObject(By.res("com.adidas.micoach:id/pair_button")), 4000);

        uiDevice.swipe(uiDevice.getDisplayWidth() / 2, uiDevice.getDisplayHeight() / 2, uiDevice.getDisplayWidth() / 2, uiDevice.getDisplayHeight() / 8, 40);

        //  com.adidas.micoach.testing:id/planChooser_expandAddPlanBtn

        uiDevice.wait(Until.hasObject(By.clazz(TextView.class).text("ADD BOTH PLANS")), 2000);

        UiObject BothPlan = uiDevice.findObject(new UiSelector().className(TextView.class).text("ADD BOTH PLANS"));
        BothPlan.click();


        uiDevice.wait(Until.hasObject(By.res("com.adidas.micoach:id/pair_button")), 1000);
        uiDevice.waitForIdle(1000);
    }

    // choose schedule your plan
    private void selectScheduleYourPlan() throws UiObjectNotFoundException {
        uiDevice.wait(Until.hasObject(By.res("com.adidas.micoach:id/pair_button")), 4000);

        uiDevice.swipe(uiDevice.getDisplayWidth()/2, uiDevice.getDisplayHeight() / 2, uiDevice.getDisplayWidth()/2, uiDevice.getDisplayHeight() / 8, 40);
        uiDevice.wait(Until.hasObject(By.clazz(TextView.class).text("Next")), 2000);

        UiObject Next = uiDevice.findObject(new UiSelector().className(TextView.class).text("Next"));
        Next.click();

        uiDevice.wait(Until.hasObject(By.res("com.adidas.micoach:id/pair_button")), 20000);
        uiDevice.waitForIdle(1000);
    }


    // choose not to sync with Calender
    private void selectCalenderNotNow() throws UiObjectNotFoundException {

        uiDevice.wait(Until.hasObject(By.clazz(TextView.class).text("NOT NOW")), 500);

        UiObject NotNow = uiDevice.findObject(new UiSelector().className(TextView.class).text("NOT NOW"));

        if (NotNow.exists() && NotNow.isClickable()) {

            NotNow.click();
            uiDevice.wait(Until.hasObject(By.res("com.adidas.micoach:id/pair_button")), 1000);
        }

        uiDevice.wait(Until.hasObject(By.res("com.adidas.micoach:id/pair_button")), 1000);
        uiDevice.waitForIdle(1000);
    }





    // choose end plan
    private void selectEndPlan() throws UiObjectNotFoundException {

        uiDevice.wait(Until.hasObject(By.res("com.adidas.micoach:id/pair_button")), 2000);

        uiDevice.click(10 * (uiDevice.getDisplayWidth() / 11), uiDevice.getDisplayHeight() / 9);

        uiDevice.wait(Until.hasObject(By.res("com.adidas.micoach:id/pair_button")), 2000);

        uiDevice.click(10 * (uiDevice.getDisplayWidth() / 11), uiDevice.getDisplayHeight() / 9);

        uiDevice.wait(Until.hasObject(By.res("com.adidas.micoach:id/pair_button")), 2000);



        uiDevice.wait(Until.hasObject(By.clazz(TextView.class).text("End plan")), 500);

        UiObject EndPlan = uiDevice.findObject(new UiSelector().className(TextView.class).text("End plan"));
        EndPlan.click();

        uiDevice.wait(Until.hasObject(By.res("com.adidas.micoach:id/pair_button")), 1000);
        uiDevice.waitForIdle(1000);
    }

    private void selectEndBothPlans() throws UiObjectNotFoundException {

        uiDevice.wait(Until.hasObject(By.res("com.adidas.micoach:id/pair_button")), 20000);

        uiDevice.click(10 * (uiDevice.getDisplayWidth() / 11), uiDevice.getDisplayHeight() / 9);

        uiDevice.wait(Until.hasObject(By.res("com.adidas.micoach:id/pair_button")), 2000);

        uiDevice.click(10 * (uiDevice.getDisplayWidth() / 11), uiDevice.getDisplayHeight() / 9);

        uiDevice.wait(Until.hasObject(By.res("com.adidas.micoach:id/pair_button")), 2000);


        uiDevice.wait(Until.hasObject(By.clazz(TextView.class).text("End plan")), 500);

        // select end plan
        UiObject EndPlan = uiDevice.findObject(new UiSelector().className(TextView.class).text("End plan"));
        EndPlan.click();

        uiDevice.wait(Until.hasObject(By.res("com.adidas.micoach:id/pair_button")), 1000);
        uiDevice.waitForIdle(1000);

        // Finish Faster - 10 K

        uiDevice.click(9 * (uiDevice.getDisplayWidth() / 10), 4 * (uiDevice.getDisplayHeight() / 11));

        uiDevice.wait(Until.hasObject(By.res("com.adidas.micoach:id/pair_button")), 2000);

        // Finish Strength & Flex

        uiDevice.click(9 * (uiDevice.getDisplayWidth() / 10), 47 * (uiDevice.getDisplayHeight() / 100));

        uiDevice.wait(Until.hasObject(By.res("com.adidas.micoach:id/pair_button")), 2000);


        // end plan

        uiDevice.click((uiDevice.getDisplayWidth() / 2), 61 * (uiDevice.getDisplayHeight() / 100));


        uiDevice.wait(Until.hasObject(By.res("com.adidas.micoach:id/pair_button")), 2000);


    }

    // confirm end plan
    private void selectConfirmEndPlan() throws UiObjectNotFoundException {

        uiDevice.wait(Until.hasObject(By.clazz(TextView.class).text("END MY PLAN")), 2000);

        UiObject EndMyPlan = uiDevice.findObject(new UiSelector().className(TextView.class).text("END MY PLAN"));
        EndMyPlan.click();

        uiDevice.wait(Until.hasObject(By.res("com.adidas.micoach:id/pair_button")), 20000);
        uiDevice.waitForIdle(1000);
    }















    public void tearDown() {
        //logger.writeLogLine(System.currentTimeMillis() + ", " + "Training plan automation done.");
        //logger.close();
        //logger = null;
    }



}








//


