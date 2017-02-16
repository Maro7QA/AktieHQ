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



/**
 * UIAutomator test for login, and after then pair and unpair Fitsmart multiple times.
 *

 *
 * start command: gradlew cATMMD/ gradlew --daemon connectedMicoachMainDebugAndroidTest
 */
public class ActivityType extends InstrumentationTestCase {


    private static final String EMAIL = "sma@gmx.de";
    private static final String PASSWORD = "aaabbb12";
    private static final String TO = "digitalsports.qa@adidas.com";
    private static final String StravaMail = "micoach.testing@hotmail.de";
    private static final String StravaPassword = "23HardWare76";


    private UiDevice uiDevice;
   // private FileLogger logger;


    public void setUp() {
        uiDevice = UiDevice.getInstance(getInstrumentation());
      //  logger = new FileLogger();
      //  logger.writeLogLine(System.currentTimeMillis() + ", " + "Fitsmart pairing UI test started...");
    }

    public void testStart() throws UiObjectNotFoundException {
        startHomeScreen();
        launchMicoach();
        //   signMail();

        //StartApp();


        // insert void Start Workout
        selectRunMenu();
        swipeToRunScreen();
        selectActivity();
        selectRunningTrack();
        pressStart();
        swipeToPause();
        pressEndWorkout();
        WorkoutDone();
        selectRunMenu();


        selectActivity();
        selectRunningTrail();
        pressStart();
        swipeToPause();
        pressEndWorkout();
        WorkoutDone();
        selectRunMenu();

        selectActivity();
        selectRunningIndoor();
        pressStart();
        swipeToPause();
        pressEndWorkout();
        WorkoutDone();
        selectRunMenu();

        selectActivity();
        selectWalking();
        pressStart();
        swipeToPause();
        pressEndWorkout();
        WorkoutDone();
        selectRunMenu();

        selectActivity();
        selectWalking();
        pressStart();
        swipeToPause();
        pressEndWorkout();
        WorkoutDone();
        selectRunMenu();

        selectActivity();
        selectCycling();
        pressStart();
        swipeToPause();
        pressEndWorkout();
        WorkoutDone();
        selectRunMenu();

        selectActivity();
        selectCyclingMB();
        pressStart();
        swipeToPause();
        pressEndWorkout();
        WorkoutDone();
        selectRunMenu();

        selectActivity();
        selectCyclingSB();
        pressStart();
        swipeToPause();
        pressEndWorkout();
        WorkoutDone();
        selectRunMenu();


        selectActivity();
        selectHiking();
        pressStart();
        swipeToPause();
        pressEndWorkout();
        WorkoutDone();
        selectRunMenu();

        selectActivity();
        selectAEROBICS();
        pressStart();
        swipeToPause();
        pressEndWorkout();
        WorkoutDone();
        selectRunMenu();

        selectActivity();
        selectPilates();
        pressStart();
        swipeToPause();
        pressEndWorkout();
        WorkoutDone();
        selectRunMenu();

        selectActivity();
        selectCircuitTraining();
        pressStart();
        swipeToPause();
        pressEndWorkout();
        WorkoutDone();
        selectRunMenu();

        selectActivity();
        selectRowingIndoor();
        pressStart();
        swipeToPause();
        pressEndWorkout();
        WorkoutDone();
        selectRunMenu();

        selectActivity();
        selectRowingOutdoor();
        pressStart();
        swipeToPause();
        pressEndWorkout();
        WorkoutDone();
        selectRunMenu();

        selectActivity();
        selectSkiing();
        pressStart();
        swipeToPause();
        pressEndWorkout();
        WorkoutDone();
        selectRunMenu();


        selectActivity();
        selectSkiingDH();
        pressStart();
        swipeToPause();
        pressEndWorkout();
        WorkoutDone();
        selectRunMenu();

        selectActivity();
        selectSkiingMount();
        pressStart();
        swipeToPause();
        pressEndWorkout();
        WorkoutDone();
        selectRunMenu();

        selectActivity();
        selectInlineSkating();
        pressStart();
        swipeToPause();
        pressEndWorkout();
        WorkoutDone();
        selectRunMenu();

        selectActivity();
        selectOther();
        pressStart();
        swipeToPause();
        pressEndWorkout();
        WorkoutDone();
        selectRunMenu();


        // selectActivity();
        // startSharing();
        // selectGmail();
        // sendMail();

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

        uiDevice.wait(Until.hasObject(By.clazz(TextView.class).text("Apps")), 500);

        UiObject AppStart  = uiDevice.findObject(new UiSelector().className(TextView.class).text("train & run beta"));
        AppStart.click();

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

    private void launchMicoach() throws UiObjectNotFoundException {
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

    private void selectRunMenu() throws UiObjectNotFoundException {
        uiDevice.wait(Until.hasObject(By.res("com.adidas.micoach:id/pair_button")), 1000);

        uiDevice.swipe(0, uiDevice.getDisplayHeight() / 2, uiDevice.getDisplayWidth(), uiDevice.getDisplayHeight() / 2, 40);
        uiDevice.wait(Until.hasObject(By.clazz(TextView.class).text("Run")), 1000);

        uiDevice.wait(Until.hasObject(By.res("com.adidas.micoach:id/pair_button")), 1000);

        //UiObject Run = uiDevice.findObject(new UiSelector().className(TextView.class).text("Run"));
        //Run.click();
        uiDevice.click((uiDevice.getDisplayWidth() / 2), ( uiDevice.getDisplayHeight() / 3));

        uiDevice.wait(Until.hasObject(By.res("com.adidas.micoach:id/pair_button")), 1000);
        uiDevice.waitForIdle(1000);


    }

    private void signMail() throws UiObjectNotFoundException {

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

        //  UiObject password = uiDevice.findObject(new UiSelector().resourceId("com.adidas.micoach.testing:id/signin_password"));

        //    uiDevice.wait(Until.hasObject(By.clazz(TextView.class).text("")), 2000);
        //  UiObject password = uiDevice.findObject(new UiSelector().className(EditText.class).text(""));

//        password.setText(PASSWORD);

        // select password object



        uiDevice.wait(Until.hasObject(By.clazz(TextView.class).text("not_existing_object")), 2000);

        uiDevice.wait(Until.hasObject(By.clazz(TextView.class).text("DONE")), 2000);
        UiObject done = uiDevice.findObject(new UiSelector().className(TextView.class).text("DONE"));
        done.click();

        uiDevice.wait(Until.hasObject(By.clazz(TextView.class).text("not_existing_object")), 10000);

    }


    // go to device section
    private void selectPairADevice() throws UiObjectNotFoundException {
        uiDevice.swipe(0, uiDevice.getDisplayHeight() / 2, uiDevice.getDisplayWidth(), uiDevice.getDisplayHeight() / 2, 40);

        uiDevice.swipe(0, uiDevice.getDisplayHeight() / 8, 0, uiDevice.getDisplayHeight() / 2, 100);

        uiDevice.wait(Until.hasObject(By.clazz(TextView.class).text("Devices")), 2000);

        UiObject devices = uiDevice.findObject(new UiSelector().className(TextView.class).text("Devices"));
        devices.click();

        uiDevice.wait(Until.hasObject(By.res("com.adidas.micoach:id/pair_button")), 2000);
        uiDevice.waitForIdle(1000);
    }

    // add Fitsmart and wait unit pair and sync is done
    private void selectAdd() throws UiObjectNotFoundException {

        uiDevice.wait(Until.hasObject(By.clazz(TextView.class).text("Add")), 2000);

        UiObject add = uiDevice.findObject(new UiSelector().className(TextView.class).text("Add"));
        add.click();

        uiDevice.wait(Until.hasObject(By.res("com.adidas.micoach:id/pair_button")), 40000);
        uiDevice.waitForIdle(1000);


        UiObject done = uiDevice.findObject(new UiSelector().className(TextView.class).text("DONE"));
        done.click();

        uiDevice.wait(Until.hasObject(By.res("com.adidas.micoach:id/pair_button")), 2000);
        uiDevice.waitForIdle(1000);

        uiDevice.click((uiDevice.getDisplayWidth() / 2), (11 * uiDevice.getDisplayHeight() / 12));

        uiDevice.wait(Until.hasObject(By.res("com.adidas.micoach:id/pair_button")), 2000);
        uiDevice.waitForIdle(1000);

    }
    // changed by MH inserted private void to check TrainingPlan access
    // choose plan from device list

    // choose free workout
    private void selectRunningTrack() throws UiObjectNotFoundException {

        uiDevice.swipe(uiDevice.getDisplayWidth() / 2, uiDevice.getDisplayHeight() / 8, uiDevice.getDisplayWidth() / 2, uiDevice.getDisplayHeight() / 2, 100);
        uiDevice.swipe(uiDevice.getDisplayWidth() / 2, uiDevice.getDisplayHeight() / 8, uiDevice.getDisplayWidth() / 2, uiDevice.getDisplayHeight() / 2, 100);

        uiDevice.wait(Until.hasObject(By.res("com.adidas.micoach:id/pair_button")), 1000);


        uiDevice.wait(Until.hasObject(By.clazz(TextView.class).text("Running (Track/Road)")), 1000);

        UiObject run = uiDevice.findObject(new UiSelector().className(TextView.class).text("Running (Track/Road)"));
        run.click();

        uiDevice.wait(Until.hasObject(By.res("com.adidas.micoach:id/pair_button")), 2000);
        uiDevice.waitForIdle(1000);

      //  logger.writeLogLine(System.currentTimeMillis() + "Running workout!!");

    }

    private void selectRunningTrail() throws UiObjectNotFoundException {

        uiDevice.swipe(uiDevice.getDisplayWidth() / 2, uiDevice.getDisplayHeight() / 8, uiDevice.getDisplayWidth() / 2, uiDevice.getDisplayHeight() / 2, 100);
        uiDevice.swipe(uiDevice.getDisplayWidth() / 2, uiDevice.getDisplayHeight() / 8, uiDevice.getDisplayWidth() / 2, uiDevice.getDisplayHeight() / 2, 100);

        uiDevice.wait(Until.hasObject(By.res("com.adidas.micoach:id/pair_button")), 1000);


        uiDevice.wait(Until.hasObject(By.clazz(TextView.class).text("Running (Trail)")), 1000);

        UiObject run = uiDevice.findObject(new UiSelector().className(TextView.class).text("Running (Trail)"));
        run.click();

        uiDevice.wait(Until.hasObject(By.res("com.adidas.micoach:id/pair_button")), 2000);
        uiDevice.waitForIdle(1000);

     //   logger.writeLogLine(System.currentTimeMillis() + "Running workout!!");

    }

    private void selectRunningIndoor() throws UiObjectNotFoundException {

        uiDevice.swipe(uiDevice.getDisplayWidth() / 2, uiDevice.getDisplayHeight() / 8, uiDevice.getDisplayWidth() / 2, uiDevice.getDisplayHeight() / 2, 100);
        uiDevice.swipe(uiDevice.getDisplayWidth() / 2, uiDevice.getDisplayHeight() / 8, uiDevice.getDisplayWidth() / 2, uiDevice.getDisplayHeight() / 2, 100);

        uiDevice.wait(Until.hasObject(By.res("com.adidas.micoach:id/pair_button")), 1000);


        uiDevice.wait(Until.hasObject(By.clazz(TextView.class).text("Running (Indoor/Treadmill)")), 1000);

        UiObject run = uiDevice.findObject(new UiSelector().className(TextView.class).text("Running (Indoor/Treadmill)"));
        run.click();

        uiDevice.wait(Until.hasObject(By.res("com.adidas.micoach:id/pair_button")), 2000);
        uiDevice.waitForIdle(1000);

     //   logger.writeLogLine(System.currentTimeMillis() + "Running workout!!");


    }

    private void selectCrossTrainer() throws UiObjectNotFoundException {

        uiDevice.swipe(uiDevice.getDisplayWidth() / 2, uiDevice.getDisplayHeight() / 8, uiDevice.getDisplayWidth() / 2, uiDevice.getDisplayHeight() / 2, 100);
        uiDevice.swipe(uiDevice.getDisplayWidth() / 2, uiDevice.getDisplayHeight() / 8, uiDevice.getDisplayWidth() / 2, uiDevice.getDisplayHeight() / 2, 100);

        uiDevice.wait(Until.hasObject(By.res("com.adidas.micoach:id/pair_button")), 1000);


        uiDevice.wait(Until.hasObject(By.clazz(TextView.class).text("Cross Trainer (Cross Trainer (Stepper/Elliptical ?))")), 1000);

        UiObject run = uiDevice.findObject(new UiSelector().className(TextView.class).text("Cross Trainer (Stepper/Elliptical ?))"));
        run.click();

        uiDevice.wait(Until.hasObject(By.res("com.adidas.micoach:id/pair_button")), 2000);
        uiDevice.waitForIdle(1000);

      //  logger.writeLogLine(System.currentTimeMillis() + "Cross Trainer workout!!");

    }


    // choose free workout
    private void selectWalking() throws UiObjectNotFoundException {

        uiDevice.wait(Until.hasObject(By.res("com.adidas.micoach:id/pair_button")), 1000);


        uiDevice.wait(Until.hasObject(By.clazz(TextView.class).text("Walking")), 1000);

        UiObject walk = uiDevice.findObject(new UiSelector().className(TextView.class).text("Walking"));
        walk.click();

        uiDevice.wait(Until.hasObject(By.res("com.adidas.micoach:id/pair_button")), 2000);
        uiDevice.waitForIdle(1000);

     //   logger.writeLogLine(System.currentTimeMillis() + "Walking workout!!");

    }


    private void selectCycling() throws UiObjectNotFoundException {


        uiDevice.wait(Until.hasObject(By.res("com.adidas.micoach:id/pair_button")), 1000);

        uiDevice.wait(Until.hasObject(By.clazz(TextView.class).text("Cycling (Road Bike)")), 1000);

        UiObject cycle = uiDevice.findObject(new UiSelector().className(TextView.class).text("Cycling (Road Bike)"));
        cycle.click();

        uiDevice.wait(Until.hasObject(By.res("com.adidas.micoach:id/pair_button")), 2000);
        uiDevice.waitForIdle(1000);

      // logger.writeLogLine(System.currentTimeMillis() + "Cycling workout!!");


    }

    private void selectCyclingMB() throws UiObjectNotFoundException {


        uiDevice.wait(Until.hasObject(By.res("com.adidas.micoach:id/pair_button")), 1000);

        uiDevice.wait(Until.hasObject(By.clazz(TextView.class).text("Cycling (Mountain Bike)")), 1000);

        UiObject cycle = uiDevice.findObject(new UiSelector().className(TextView.class).text("Cycling (Mountain Bike)"));
        cycle.click();

        uiDevice.wait(Until.hasObject(By.res("com.adidas.micoach:id/pair_button")), 2000);
        uiDevice.waitForIdle(1000);

       // logger.writeLogLine(System.currentTimeMillis() + "Cycling workout!!");


    }

    private void selectCyclingSB() throws UiObjectNotFoundException {


        uiDevice.wait(Until.hasObject(By.res("com.adidas.micoach:id/pair_button")), 1000);

        uiDevice.wait(Until.hasObject(By.clazz(TextView.class).text("Cycling (Stationary Bike)")), 1000);

        UiObject cycle = uiDevice.findObject(new UiSelector().className(TextView.class).text("Cycling (Stationary Bike)"));
        cycle.click();

        uiDevice.wait(Until.hasObject(By.res("com.adidas.micoach:id/pair_button")), 2000);
        uiDevice.waitForIdle(1000);

      //  logger.writeLogLine(System.currentTimeMillis() + "Cycling workout!!");


    }

    private void selectHiking() throws UiObjectNotFoundException {


        uiDevice.wait(Until.hasObject(By.res("com.adidas.micoach:id/pair_button")), 1000);

        uiDevice.wait(Until.hasObject(By.clazz(TextView.class).text("Hiking")), 1000);

        UiObject hike = uiDevice.findObject(new UiSelector().className(TextView.class).text("Hiking"));
        hike.click();

        uiDevice.wait(Until.hasObject(By.res("com.adidas.micoach:id/pair_button")), 2000);
        uiDevice.waitForIdle(1000);

       // logger.writeLogLine(System.currentTimeMillis() + "Hiking workout!!");


    }


    private void selectAEROBICS() throws UiObjectNotFoundException {


        uiDevice.wait(Until.hasObject(By.res("com.adidas.micoach:id/pair_button")), 1000);

        uiDevice.swipe(uiDevice.getDisplayWidth() / 2, uiDevice.getDisplayHeight() / 2, uiDevice.getDisplayWidth() / 2, uiDevice.getDisplayHeight() / 8, 100);
        uiDevice.swipe(uiDevice.getDisplayWidth() / 2, uiDevice.getDisplayHeight() / 2, uiDevice.getDisplayWidth() / 2, uiDevice.getDisplayHeight() / 8, 100);

        uiDevice.wait(Until.hasObject(By.clazz(TextView.class).text("Aerobics")), 1000);

        UiObject aero = uiDevice.findObject(new UiSelector().className(TextView.class).text("Aerobics"));
        aero.click();

        uiDevice.wait(Until.hasObject(By.res("com.adidas.micoach:id/pair_button")), 2000);
        uiDevice.waitForIdle(1000);

     //   logger.writeLogLine(System.currentTimeMillis() + "AEROBICS workout!!");


    }

    private void selectPilates() throws UiObjectNotFoundException {


        uiDevice.wait(Until.hasObject(By.res("com.adidas.micoach:id/pair_button")), 1000);

        uiDevice.wait(Until.hasObject(By.clazz(TextView.class).text("Yoga/Pilates")), 1000);

        UiObject yoga = uiDevice.findObject(new UiSelector().className(TextView.class).text("Yoga/Pilates"));
        yoga.click();

        uiDevice.wait(Until.hasObject(By.res("com.adidas.micoach:id/pair_button")), 2000);
        uiDevice.waitForIdle(1000);

     //   logger.writeLogLine(System.currentTimeMillis() + "Yoga/Pilates workout!!");


    }


    private void selectCircuitTraining() throws UiObjectNotFoundException {


        uiDevice.wait(Until.hasObject(By.res("com.adidas.micoach:id/pair_button")), 1000);

        uiDevice.wait(Until.hasObject(By.clazz(TextView.class).text("Circuit Training")), 1000);

        UiObject ct = uiDevice.findObject(new UiSelector().className(TextView.class).text("Circuit Training"));
        ct.click();

        uiDevice.wait(Until.hasObject(By.res("com.adidas.micoach:id/pair_button")), 2000);
        uiDevice.waitForIdle(1000);

      //  logger.writeLogLine(System.currentTimeMillis() + "Circuit Training workout!!");


    }

    private void selectRowingIndoor() throws UiObjectNotFoundException {


        uiDevice.wait(Until.hasObject(By.res("com.adidas.micoach:id/pair_button")), 1000);

        uiDevice.wait(Until.hasObject(By.clazz(TextView.class).text("Rowing (Indoor)")), 1000);

        UiObject row = uiDevice.findObject(new UiSelector().className(TextView.class).text("Rowing (Indoor)"));
        row.click();

        uiDevice.wait(Until.hasObject(By.res("com.adidas.micoach:id/pair_button")), 2000);
        uiDevice.waitForIdle(1000);

       // logger.writeLogLine(System.currentTimeMillis() + "Rowing workout!!");


    }

    private void selectRowingOutdoor() throws UiObjectNotFoundException {


        uiDevice.wait(Until.hasObject(By.res("com.adidas.micoach:id/pair_button")), 1000);

        uiDevice.wait(Until.hasObject(By.clazz(TextView.class).text("Rowing (Outdoor)")), 1000);

        UiObject row = uiDevice.findObject(new UiSelector().className(TextView.class).text("Rowing (Outdoor)"));
        row.click();

        uiDevice.wait(Until.hasObject(By.res("com.adidas.micoach:id/pair_button")), 2000);
        uiDevice.waitForIdle(1000);

      //  logger.writeLogLine(System.currentTimeMillis() + "Rowing workout!!");


    }

    private void selectSkiing() throws UiObjectNotFoundException {

        uiDevice.wait(Until.hasObject(By.res("com.adidas.micoach:id/pair_button")), 1000);

        uiDevice.waitForIdle(1000);
        uiDevice.swipe(uiDevice.getDisplayWidth() / 2, uiDevice.getDisplayHeight() / 2, uiDevice.getDisplayWidth() / 2, uiDevice.getDisplayHeight() / 8, 100);
        uiDevice.swipe(uiDevice.getDisplayWidth() / 2, uiDevice.getDisplayHeight() / 2, uiDevice.getDisplayWidth() / 2, uiDevice.getDisplayHeight() / 8, 100);

        uiDevice.wait(Until.hasObject(By.clazz(TextView.class).text("Skiing (Nordic/Cross-Country)")), 1000);

        UiObject ski = uiDevice.findObject(new UiSelector().className(TextView.class).text("Skiing (Nordic/Cross-Country)"));
        ski.click();

        uiDevice.wait(Until.hasObject(By.res("com.adidas.micoach:id/pair_button")), 2000);
        uiDevice.waitForIdle(1000);

     //   logger.writeLogLine(System.currentTimeMillis() + "Skiing workout!!");


    }

    private void selectSkiingDH() throws UiObjectNotFoundException {

        uiDevice.wait(Until.hasObject(By.res("com.adidas.micoach:id/pair_button")), 1000);

        uiDevice.waitForIdle(1000);
        uiDevice.swipe(uiDevice.getDisplayWidth() / 2, uiDevice.getDisplayHeight() / 2, uiDevice.getDisplayWidth() / 2, uiDevice.getDisplayHeight() / 8, 100);
        uiDevice.swipe(uiDevice.getDisplayWidth() / 2, uiDevice.getDisplayHeight() / 2, uiDevice.getDisplayWidth() / 2, uiDevice.getDisplayHeight() / 8, 100);

        uiDevice.wait(Until.hasObject(By.clazz(TextView.class).text("Skiing (Downhill/Snowboarding)")), 1000);

        UiObject ski = uiDevice.findObject(new UiSelector().className(TextView.class).text("Skiing (Downhill/Snowboarding)"));
        ski.click();

        uiDevice.wait(Until.hasObject(By.res("com.adidas.micoach:id/pair_button")), 2000);
        uiDevice.waitForIdle(1000);

       // logger.writeLogLine(System.currentTimeMillis() + "Skiing workout!!");


    }

    private void selectSkiingMount() throws UiObjectNotFoundException {

        uiDevice.wait(Until.hasObject(By.res("com.adidas.micoach:id/pair_button")), 1000);

        uiDevice.waitForIdle(1000);
        uiDevice.swipe(uiDevice.getDisplayWidth() / 2, uiDevice.getDisplayHeight() / 2, uiDevice.getDisplayWidth() / 2, uiDevice.getDisplayHeight() / 8, 100);
        uiDevice.swipe(uiDevice.getDisplayWidth() / 2, uiDevice.getDisplayHeight() / 2, uiDevice.getDisplayWidth() / 2, uiDevice.getDisplayHeight() / 8, 100);

        uiDevice.wait(Until.hasObject(By.clazz(TextView.class).text("Skiing (Mountaineering)")), 1000);

        UiObject ski = uiDevice.findObject(new UiSelector().className(TextView.class).text("Skiing (Mountaineering)"));
        ski.click();

        uiDevice.wait(Until.hasObject(By.res("com.adidas.micoach:id/pair_button")), 2000);
        uiDevice.waitForIdle(1000);

      //
        //  logger.writeLogLine(System.currentTimeMillis() + "Skiing workout!!");


    }

    private void selectInlineSkating() throws UiObjectNotFoundException {

        uiDevice.wait(Until.hasObject(By.res("com.adidas.micoach:id/pair_button")), 1000);

        uiDevice.waitForIdle(1000);
        uiDevice.swipe(uiDevice.getDisplayWidth() / 2, uiDevice.getDisplayHeight() / 2, uiDevice.getDisplayWidth() / 2, uiDevice.getDisplayHeight() / 8, 100);
        uiDevice.swipe(uiDevice.getDisplayWidth() / 2, uiDevice.getDisplayHeight() / 2, uiDevice.getDisplayWidth() / 2, uiDevice.getDisplayHeight() / 8, 100);

        uiDevice.wait(Until.hasObject(By.clazz(TextView.class).text("Inline Skating")), 1000);

        UiObject ski = uiDevice.findObject(new UiSelector().className(TextView.class).text("Inline Skating"));
        ski.click();

        uiDevice.wait(Until.hasObject(By.res("com.adidas.micoach:id/pair_button")), 2000);
        uiDevice.waitForIdle(1000);

     //   logger.writeLogLine(System.currentTimeMillis() + "Inline Skating workout!!");


    }

    private void selectOther() throws UiObjectNotFoundException {

        uiDevice.wait(Until.hasObject(By.res("com.adidas.micoach:id/pair_button")), 1000);

        uiDevice.waitForIdle(1000);
        uiDevice.swipe(uiDevice.getDisplayWidth() / 2, uiDevice.getDisplayHeight() / 2, uiDevice.getDisplayWidth() / 2, uiDevice.getDisplayHeight() / 8, 100);
        uiDevice.swipe(uiDevice.getDisplayWidth() / 2, uiDevice.getDisplayHeight() / 2, uiDevice.getDisplayWidth() / 2, uiDevice.getDisplayHeight() / 8, 100);

        uiDevice.wait(Until.hasObject(By.clazz(TextView.class).text("Other")), 1000);

        UiObject ski = uiDevice.findObject(new UiSelector().className(TextView.class).text("Other"));
        ski.click();

        uiDevice.wait(Until.hasObject(By.res("com.adidas.micoach:id/pair_button")), 2000);
        uiDevice.waitForIdle(1000);

       // logger.writeLogLine(System.currentTimeMillis() + "Other workout!!");


    }

    private void selectActivity() throws UiObjectNotFoundException {

        uiDevice.wait(Until.hasObject(By.clazz(TextView.class).text("not_existing_object")), 20000);



        //  UiObject activity = uiDevice.findObject(new UiSelector().resourceId("com.adidas.micoach.testing:id/choose_activity_type"));
        // activity.click();

        uiDevice.click((9 * uiDevice.getDisplayWidth() / 11), (uiDevice.getDisplayHeight() / 11));
        uiDevice.wait(Until.hasObject(By.clazz(TextView.class).text("not_existing_object")), 1000);


        uiDevice.wait(Until.hasObject(By.res("com.adidas.micoach:id/pair_button")), 1000);
        uiDevice.waitForIdle(1000);

        //CYCLING (ROAD BIKE); HIKING; SKIING (NORDIC/CROSS-COUNTRY)
    }

    private void selectSwimming() throws UiObjectNotFoundException {

        uiDevice.wait(Until.hasObject(By.res("com.adidas.micoach:id/pair_button")), 1000);

        uiDevice.swipe(uiDevice.getDisplayWidth() / 2, uiDevice.getDisplayHeight() / 2, uiDevice.getDisplayWidth() / 2, uiDevice.getDisplayHeight() / 8, 100);
        uiDevice.swipe(uiDevice.getDisplayWidth() / 2, uiDevice.getDisplayHeight() / 2, uiDevice.getDisplayWidth() / 2, uiDevice.getDisplayHeight() / 8, 100);
        uiDevice.swipe(uiDevice.getDisplayWidth() / 2, uiDevice.getDisplayHeight() / 2, uiDevice.getDisplayWidth() / 2, uiDevice.getDisplayHeight() / 8, 100);
        uiDevice.swipe(uiDevice.getDisplayWidth() / 2, uiDevice.getDisplayHeight() / 2, uiDevice.getDisplayWidth() / 2, uiDevice.getDisplayHeight() / 8, 100);

        uiDevice.wait(Until.hasObject(By.clazz(TextView.class).text("Swimming")), 1000);

        UiObject swim = uiDevice.findObject(new UiSelector().className(TextView.class).text("Swimming"));
        swim.click();

        uiDevice.wait(Until.hasObject(By.res("com.adidas.micoach:id/pair_button")), 2000);
        uiDevice.waitForIdle(1000);

     //   logger.writeLogLine(System.currentTimeMillis() + "Swimming workout!!");


    }

    // press Start
    // press Run
    private void pressStart() throws UiObjectNotFoundException {
        // press Run button
        uiDevice.wait(Until.hasObject(By.res("com.adidas.micoach:id/pair_button")), 4000);


        uiDevice.wait(Until.hasObject(By.clazz(TextView.class).text("DISMISS")), 1000);

        UiObject dismiss = uiDevice.findObject(new UiSelector().className(TextView.class).text("DISMISS"));

        if (dismiss.exists() && dismiss.isClickable()) {
          //  logger.writeLogLine(System.currentTimeMillis() + ", " + "(/) Fitsmart reached goal");
            dismiss.click();
            uiDevice.wait(Until.hasObject(By.res("com.adidas.micoach:id/pair_button")), 1000);
            uiDevice.click((uiDevice.getDisplayWidth() / 2), (7 * uiDevice.getDisplayHeight() / 10));
            uiDevice.wait(Until.hasObject(By.res("com.adidas.micoach:id/pair_button")), 1000);
            uiDevice.waitForIdle(1000);
        }

        uiDevice.wait(Until.hasObject(By.res("com.adidas.micoach:id/pair_button")), 1000);
        uiDevice.click((uiDevice.getDisplayWidth() / 2), (8 * uiDevice.getDisplayHeight() / 10));


        uiDevice.wait(Until.hasObject(By.clazz(Button.class).text("Allow")), 1000);

        UiObject allow = uiDevice.findObject(new UiSelector().className(Button.class).text("Allow"));

        if (allow.exists() && allow.isClickable()) {
            allow.click();
            uiDevice.wait(Until.hasObject(By.res("com.adidas.micoach:id/pair_button")), 1000);

        }


        uiDevice.wait(Until.hasObject(By.res("com.adidas.micoach:id/pair_button")), 2000);


        // press "GO ANYWAY" Button
        //   uiDevice.click((uiDevice.getDisplayWidth() / 2), (7 * uiDevice.getDisplayHeight() / 11));

        //   uiDevice.wait(Until.hasObject(By.res("com.adidas.micoach:id/pair_button")), 100);

        //   uiDevice.waitForIdle(1000);
    }


    // swipe StartButton

    private void swipeToPause() throws UiObjectNotFoundException {


        uiDevice.waitForIdle(10000);

        uiDevice.wait(Until.hasObject(By.clazz(TextView.class).text("not_existing_object")), 10000); // Workout length

        UiObject ExitMap = uiDevice.findObject(new UiSelector().resourceId("com.adidas.micoach.testing:id/in_workout_map_exit_button"));

        if (ExitMap.exists() && ExitMap.isClickable()) {
            ExitMap.click();
            uiDevice.wait(Until.hasObject(By.res("com.adidas.micoach:id/pair_button")), 3000);
        }


        uiDevice.swipe(0, 10 * (uiDevice.getDisplayHeight() / 11), uiDevice.getDisplayWidth(), 10 * (uiDevice.getDisplayHeight() / 11), 100);
        uiDevice.waitForIdle(10000);
        uiDevice.wait(Until.hasObject(By.clazz(TextView.class).text("not_existing_object")), 6000);
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


    private void confirmEndWorkout() {
        uiDevice.wait(Until.hasObject(By.clazz(Button.class).text("Yes")), 2000);

        UiObject YesWo = uiDevice.findObject(new UiSelector().className(Button.class).text("Yes"));
        try {
            YesWo.click();
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }

        uiDevice.wait(Until.hasObject(By.clazz(TextView.class).text("Workout Summary")), 4000);
        uiDevice.waitForIdle(1000);
        uiDevice.wait(Until.hasObject(By.clazz(TextView.class).text("not_existing_object")), 60000);

    }

    private void WorkoutDone() {
        // confirm Workout
        uiDevice.wait(Until.hasObject(By.res("com.adidas.micoach:id/pair_button")), 2000);

        uiDevice.click(10 * (uiDevice.getDisplayWidth() / 11), uiDevice.getDisplayHeight() / 9);

        uiDevice.wait(Until.hasObject(By.res("com.adidas.micoach:id/pair_button")), 2000);

        // go back to previous screen
        uiDevice.click((uiDevice.getDisplayWidth() / 10), (uiDevice.getDisplayHeight() / 11));
        uiDevice.wait(Until.hasObject(By.res("com.adidas.micoach:id/pair_button")), 2000);

    }


// click on ActivityType button com.adidas.micoach.beta:id/action_share_goal

    public void startSharing() throws UiObjectNotFoundException {
        uiDevice.wait(Until.hasObject(By.clazz(TextView.class).text("not_existing_object")), 30000);

        UiObject goal_sharing = uiDevice.findObject(new UiSelector().resourceId("com.adidas.micoach:id/action_share_goal"));
        goal_sharing.click();

        uiDevice.waitForIdle(1000);
        uiDevice.wait(Until.hasObject(By.clazz(TextView.class).text("not_existing_object")), 2000);
        UiObject share = uiDevice.findObject(new UiSelector().className(TextView.class).text("SHARE"));
        share.click();
        uiDevice.wait(Until.hasObject(By.clazz(TextView.class).text("not_existing_object")), 2000);


    }

//Select Mail account. Test phone has a GMAIL digital sports test account

    public void selectGmail() throws UiObjectNotFoundException{

        uiDevice.swipe((3 * uiDevice.getDisplayWidth())/4, 2 * uiDevice.getDisplayHeight() / 3, (3 * uiDevice.getDisplayWidth())/4, uiDevice.getDisplayHeight() / 10, 40);

        uiDevice.wait(Until.hasObject(By.clazz(TextView.class).text("not_existing_object")), 4000);

        UiObject Gmail = uiDevice.findObject(new UiSelector().className(TextView.class).text("Gmail"));

        Gmail.click();

        uiDevice.waitForIdle(1000);
        uiDevice.wait(Until.hasObject(By.clazz(TextView.class).text("not_existing_object")), 1000);
    }


    //Set mail address to Martin Hoffmeister and send it out
    public void sendMail() throws UiObjectNotFoundException {
        UiObject to = uiDevice.findObject(new UiSelector().resourceId("com.google.android.gm:id/to"));
        to.setText(TO);
        uiDevice.waitForIdle(1000);
        uiDevice.wait(Until.hasObject(By.clazz(TextView.class).text("not_existing_object")), 1000);

        UiObject send = uiDevice.findObject(new UiSelector().resourceId("com.google.android.gm:id/send"));
        send.click();
       // logger.writeLogLine(System.currentTimeMillis() + " ActivityType done");
        uiDevice.waitForIdle(1000);
        uiDevice.wait(Until.hasObject(By.clazz(TextView.class).text("not_existing_object")), 1000);

        // go back to previous screen
        uiDevice.click((uiDevice.getDisplayWidth() / 10), (uiDevice.getDisplayHeight() / 11));

    }

    // go to ActivityType section
    private void selectSharing() throws UiObjectNotFoundException {
        uiDevice.swipe(0, uiDevice.getDisplayHeight() / 2, uiDevice.getDisplayWidth(), uiDevice.getDisplayHeight() / 2, 40);
        uiDevice.wait(Until.hasObject(By.clazz(TextView.class).text("ActivityType")), 2000);
        uiDevice.swipe(0, uiDevice.getDisplayHeight() / 2, 0, uiDevice.getDisplayHeight() / 8, 100);

        UiObject devices = uiDevice.findObject(new UiSelector().className(TextView.class).text("ActivityType"));
        devices.click();

        uiDevice.wait(Until.hasObject(By.res("com.adidas.micoach:id/pair_button")), 15000);
        uiDevice.waitForIdle(1000);
    }

    // connect to Strava
    private void selectStrava() throws UiObjectNotFoundException {

        uiDevice.wait(Until.hasObject(By.clazz(TextView.class).text("Strava")), 2000);
        uiDevice.wait(Until.hasObject(By.clazz(TextView.class).text("Connected accounts")), 2000);

        UiObject strava = uiDevice.findObject(new UiSelector().className(TextView.class).text("Strava"));
        UiObject ConnectedAcc = uiDevice.findObject(new UiSelector().className(TextView.class).text("Connected accounts"));

        if (ConnectedAcc.exists()) {
           // logger.writeLogLine(System.currentTimeMillis() + ", " + "(/) Strava already connected");
            uiDevice.wait(Until.hasObject(By.clazz(TextView.class).text("Workouts on FIT SMART")), 1000);
            uiDevice.waitForIdle(1000);
        } else {
          //  logger.writeLogLine(System.currentTimeMillis() + ", " + "(!) Strava not connected");
            strava.click();
            uiDevice.wait(Until.hasObject(By.clazz(TextView.class).text("Workouts on FIT SMART")), 2000);
            uiDevice.waitForIdle(1000);

            uiDevice.wait(Until.hasObject(By.res("com.adidas.micoach:id/pair_button")), 8000);
            UiObject Strava_Mail = uiDevice.findObject(new UiSelector().resourceId("email"));
            Strava_Mail.setText(StravaMail);
            uiDevice.waitForIdle(1000);
            uiDevice.wait(Until.hasObject(By.clazz(TextView.class).text("not_existing_object")), 1000);

            UiObject PassSt = uiDevice.findObject(new UiSelector().resourceId("password"));
            PassSt.setText(StravaPassword);

            uiDevice.swipe(0, uiDevice.getDisplayHeight() / 2, 0, uiDevice.getDisplayHeight() / 8, 100);

            UiObject login = uiDevice.findObject(new UiSelector().resourceId("login-button"));
            login.click();


         //   logger.writeLogLine(System.currentTimeMillis() + " ActivityType with Strava");
            uiDevice.waitForIdle(1000);
            uiDevice.wait(Until.hasObject(By.clazz(TextView.class).text("not_existing_object")), 8000);

            // confirm authentication
            uiDevice.click((uiDevice.getDisplayWidth() / 3), (5 * uiDevice.getDisplayHeight() / 7));
            uiDevice.wait(Until.hasObject(By.clazz(TextView.class).text("not_existing_object")), 3000);

            // go back to sharing screen
            uiDevice.click((uiDevice.getDisplayWidth() / 10), (uiDevice.getDisplayHeight() / 11));
            uiDevice.wait(Until.hasObject(By.clazz(TextView.class).text("not_existing_object")), 10000);
          //  logger.writeLogLine(System.currentTimeMillis() + " ActivityType with Strava successful!!");

            // share every workout
            strava.click();
            uiDevice.wait(Until.hasObject(By.clazz(TextView.class).text("not_existing_object")), 1000);
            uiDevice.wait(Until.hasObject(By.res("com.adidas.micoach:id/sharing_options_every_workout")), 8000);
            UiObject everyWO = uiDevice.findObject(new UiSelector().resourceId("com.adidas.micoach:id/sharing_options_every_workout"));
            everyWO.click();
         //   logger.writeLogLine(System.currentTimeMillis() + " ActivityType every workout!!");
            uiDevice.wait(Until.hasObject(By.clazz(TextView.class).text("not_existing_object")), 1000);

            //go back to ActivityType menu
            uiDevice.click((uiDevice.getDisplayWidth() / 10), (uiDevice.getDisplayHeight() / 11));
            uiDevice.wait(Until.hasObject(By.clazz(TextView.class).text("not_existing_object")), 6000);


        }


    }

    //login to Strava
    public void loginStrava() throws UiObjectNotFoundException {
        uiDevice.wait(Until.hasObject(By.res("com.adidas.micoach:id/pair_button")), 8000);
        UiObject Strava_Mail = uiDevice.findObject(new UiSelector().resourceId("email"));
        Strava_Mail.setText(StravaMail);
        uiDevice.waitForIdle(1000);
        uiDevice.wait(Until.hasObject(By.clazz(TextView.class).text("not_existing_object")), 1000);

        UiObject send = uiDevice.findObject(new UiSelector().resourceId("password"));
        send.click();
     //   logger.writeLogLine(System.currentTimeMillis() + " ActivityType with Strava");
        uiDevice.waitForIdle(1000);
        uiDevice.wait(Until.hasObject(By.clazz(TextView.class).text("not_existing_object")), 3000);

        uiDevice.click((uiDevice.getDisplayWidth() / 3), (3 * uiDevice.getDisplayHeight() / 7));

    }


    public void tearDown() {
      //  logger.writeLogLine(System.currentTimeMillis() + ", " + "Training plan automation done.");
       // logger.close();

    }


}

