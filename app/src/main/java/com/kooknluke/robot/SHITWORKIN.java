package com.kooknluke.robot;

import android.content.Context;
import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.atap.tangoservice.Tango;
import com.google.atap.tangoservice.TangoAreaDescriptionMetaData;
import com.google.atap.tangoservice.TangoConfig;
import com.google.atap.tangoservice.TangoCoordinateFramePair;
import com.google.atap.tangoservice.TangoErrorException;
import com.google.atap.tangoservice.TangoEvent;
import com.google.atap.tangoservice.TangoInvalidException;
import com.google.atap.tangoservice.TangoOutOfDateException;
import com.google.atap.tangoservice.TangoPoseData;
import com.google.atap.tangoservice.TangoXyzIjData;

import org.w3c.dom.Text;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import driver.UsbSerialDriver;
import driver.UsbSerialPort;
import driver.UsbSerialProber;


public class SHITWORKIN extends ActionBarActivity {

    private TextView txtAdf;
    private TextView txtXView;
    private TextView txtYView;
    private TextView txtZView;


    private static final int SECS_TO_MILLISECS = 1000;
    private Tango mTango;
    private TangoConfig mConfig;
    private TextView mUuidTextView;
    private TextView mRelocalizationTextView;

    private Button mSaveAdfButton;
    private Button mFirstPersonButton;
    private Button mThirdPersonButton;
    private Button mTopDownButton;

    private double mPreviousPoseTimeStamp;
    private double mTimeToNextUpdate = UPDATE_INTERVAL_MS;

    private boolean mIsRelocalized;
    private boolean mIsLearningMode;
    private boolean mIsConstantSpaceRelocalize;

    private int qX;
    private int qY;
    private int qZ;


    private static final double UPDATE_INTERVAL_MS = 100.0;
    private static final DecimalFormat FORMAT_THREE_DECIMAL = new DecimalFormat("00.000");

    private final Object mSharedLock = new Object();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shitworkin);

        final Button btnFwd = (Button) findViewById(R.id.btnFwd);
        final Button btnE = (Button) findViewById(R.id.btnStop);
        final Button btnA = (Button) findViewById(R.id.btnLeft);
        final Button btnD = (Button) findViewById(R.id.btnRight);
        final Button btnS = (Button) findViewById(R.id.btnBack);
        txtAdf = (TextView) findViewById(R.id.txtADFName);
        txtXView = (TextView) findViewById(R.id.txtXView);
        txtYView = (TextView) findViewById(R.id.txtYView);
        txtZView = (TextView) findViewById(R.id.txtZView);

        mTango = new Tango(this);
        mConfig = new TangoConfig();

        mConfig = setTangoConfig(mTango, true);

        btnFwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                clickW();

            }
        });

        btnE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                clickE();

            }
        });

        btnA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                clickA();

            }
        });

        btnD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                clickD();

            }
        });

        btnS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                clickS();

            }
        });
    }

    public void clickW(){

        // Find all available drivers from attached devices.
        UsbManager manager = (UsbManager) getSystemService(Context.USB_SERVICE);
        List<UsbSerialDriver> availableDrivers = UsbSerialProber.getDefaultProber().findAllDrivers(manager);
        if (availableDrivers.isEmpty()) {
            return;
        }

        // Open a connection to the first available driver.
        UsbSerialDriver driver = availableDrivers.get(0);
        UsbDeviceConnection connection = manager.openDevice(driver.getDevice());
        if (connection == null) {
            // You probably need to call UsbManager.requestPermission(driver.getDevice(), ..)
            return;
        }

        // Read some data! Most have just one port (port 0).
        UsbSerialPort port = driver.getPorts().get(0);

        try {
            port.open(connection);
            port.setParameters(115200, UsbSerialPort.DATABITS_8, UsbSerialPort.STOPBITS_1, UsbSerialPort.PARITY_NONE);
            byte buffer[] = new byte[16];
            buffer = "w".getBytes();
            port.write(buffer, 360);
            //int numBytesRead = port.read(buffer, 1000);
            //Log.d(TAG, "Read " + numBytesRead + " bytes.");
            port.close();
        } catch (IOException e) {
            // Deal with error.
        }

    }

    public void clickE(){

        // Find all available drivers from attached devices.
        UsbManager manager = (UsbManager) getSystemService(Context.USB_SERVICE);
        List<UsbSerialDriver> availableDrivers = UsbSerialProber.getDefaultProber().findAllDrivers(manager);
        if (availableDrivers.isEmpty()) {
            return;
        }

// Open a connection to the first available driver.
        UsbSerialDriver driver = availableDrivers.get(0);
        UsbDeviceConnection connection = manager.openDevice(driver.getDevice());
        if (connection == null) {
            // You probably need to call UsbManager.requestPermission(driver.getDevice(), ..)
            return;
        }

        // Read some data! Most have just one port (port 0).
        UsbSerialPort port = driver.getPorts().get(0);

        try {
            port.open(connection);
            port.setParameters(115200, UsbSerialPort.DATABITS_8, UsbSerialPort.STOPBITS_1, UsbSerialPort.PARITY_NONE);
            byte buffer[] = new byte[16];
            buffer = "e".getBytes();
            port.write(buffer, 360);
//            int numBytesRead = port.read(buffer, 1000);
//            Log.d(TAG, "Read " + numBytesRead + " bytes.");
            port.close();
        } catch (IOException e) {
            // Deal with error.
        }

    }

    public void clickA(){

        // Find all available drivers from attached devices.
        UsbManager manager = (UsbManager) getSystemService(Context.USB_SERVICE);
        List<UsbSerialDriver> availableDrivers = UsbSerialProber.getDefaultProber().findAllDrivers(manager);
        if (availableDrivers.isEmpty()) {
            return;
        }

        // Open a connection to the first available driver.
        UsbSerialDriver driver = availableDrivers.get(0);
        UsbDeviceConnection connection = manager.openDevice(driver.getDevice());
        if (connection == null) {
            // You probably need to call UsbManager.requestPermission(driver.getDevice(), ..)
            return;
        }

        // Read some data! Most have just one port (port 0).
        UsbSerialPort port = driver.getPorts().get(0);

        try {
            port.open(connection);
            port.setParameters(115200, UsbSerialPort.DATABITS_8, UsbSerialPort.STOPBITS_1, UsbSerialPort.PARITY_NONE);
            byte buffer[] = new byte[16];
            buffer = "a".getBytes();
            port.write(buffer, 360);
//            int numBytesRead = port.read(buffer, 1000);
//            Log.d(TAG, "Read " + numBytesRead + " bytes.");
            port.close();
        } catch (IOException e) {
            // Deal with error.
        }

    }

    public void clickD(){

        // Find all available drivers from attached devices.
        UsbManager manager = (UsbManager) getSystemService(Context.USB_SERVICE);
        List<UsbSerialDriver> availableDrivers = UsbSerialProber.getDefaultProber().findAllDrivers(manager);
        if (availableDrivers.isEmpty()) {
            return;
        }

// Open a connection to the first available driver.
        UsbSerialDriver driver = availableDrivers.get(0);
        UsbDeviceConnection connection = manager.openDevice(driver.getDevice());
        if (connection == null) {
            // You probably need to call UsbManager.requestPermission(driver.getDevice(), ..)
            return;
        }

        // Read some data! Most have just one port (port 0).
        UsbSerialPort port = driver.getPorts().get(0);

        try {
            port.open(connection);
            port.setParameters(115200, UsbSerialPort.DATABITS_8, UsbSerialPort.STOPBITS_1, UsbSerialPort.PARITY_NONE);
            byte buffer[] = new byte[16];
            buffer = "d".getBytes();
            port.write(buffer, 360);
//            int numBytesRead = port.read(buffer, 1000);
//            Log.d(TAG, "Read " + numBytesRead + " bytes.");
            port.close();
        } catch (IOException e) {
            // Deal with error.
        }

    }

    public void clickS() {

        // Find all available drivers from attached devices.
        UsbManager manager = (UsbManager) getSystemService(Context.USB_SERVICE);
        List<UsbSerialDriver> availableDrivers = UsbSerialProber.getDefaultProber().findAllDrivers(manager);
        if (availableDrivers.isEmpty()) {
            return;
        }

        // Open a connection to the first available driver.
        UsbSerialDriver driver = availableDrivers.get(0);
        UsbDeviceConnection connection = manager.openDevice(driver.getDevice());
        if (connection == null) {
            // You probably need to call UsbManager.requestPermission(driver.getDevice(), ..)
            return;
        }

        // Read some data! Most have just one port (port 0).
        UsbSerialPort port = driver.getPorts().get(0);

        try {
            port.open(connection);
            port.setParameters(115200, UsbSerialPort.DATABITS_8, UsbSerialPort.STOPBITS_1, UsbSerialPort.PARITY_NONE);
            byte buffer[] = new byte[16];
            buffer = "s".getBytes();
            port.write(buffer, 360);
            //int numBytesRead = port.read(buffer, 1000);
            //Log.d(TAG, "Read " + numBytesRead + " bytes.");
            port.close();
        } catch (IOException e) {
            // Deal with error.
        }

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_shitworkin, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();

        try {
            setUpTangoListeners();
        } catch (TangoErrorException e) {
            Toast.makeText(getApplicationContext(), "T Error", Toast.LENGTH_SHORT)
                    .show();
        } catch (SecurityException e) {
            Toast.makeText(getApplicationContext(), "Permissions", Toast.LENGTH_SHORT)
                    .show();
        }

        try {
            mTango.connect(mConfig);
        } catch (TangoOutOfDateException e) {
            Toast.makeText(getApplicationContext(), "Out of date", Toast.LENGTH_SHORT).show();
        } catch (TangoErrorException e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Tango Error", Toast.LENGTH_SHORT)
                    .show();
        } catch (TangoInvalidException e) {
            Toast.makeText(getApplicationContext(), "Tango Invalid", Toast.LENGTH_SHORT)
                    .show();
        }

        qX = TangoPoseData.INDEX_ROTATION_X;
        qY = TangoPoseData.INDEX_ROTATION_Y;
        qZ = TangoPoseData.INDEX_ROTATION_Z;

        //Toast.makeText(getApplicationContext(), "X: " + Integer.toString(qX), Toast.LENGTH_SHORT);

        txtXView.setText(Boolean.toString(mIsRelocalized));
        txtYView.setText(Integer.toString(qX));
//        txtZView.setText(qZ);
    }

    private TangoConfig setTangoConfig(Tango tango, boolean isLoadAdf) {
        TangoConfig config;
        config = tango.getConfig(TangoConfig.CONFIG_TYPE_DEFAULT);

        if (isLoadAdf) {
            ArrayList<String> fullUUIDList = new ArrayList<String>();
            // Returns a list of ADFs with their UUIDs
            fullUUIDList = tango.listAreaDescriptions();
            // Load the latest ADF if ADFs are found.
            if (fullUUIDList.size() > 0) {
                config.putString(TangoConfig.KEY_STRING_AREADESCRIPTION,
                        fullUUIDList.get(fullUUIDList.size() - 1));
                TangoAreaDescriptionMetaData meta = new TangoAreaDescriptionMetaData();
                meta = mTango.loadAreaDescriptionMetaData(fullUUIDList.get(fullUUIDList.size() - 1));
                byte[] name = meta.get(TangoAreaDescriptionMetaData.KEY_NAME);
                if(name != null){
                    String nameByte = new String(name);
                    txtAdf.setText(nameByte);
                }

            }
        }
        return config;
    }

    private void setUpTangoListeners() {

        // Set Tango Listeners for Poses Device wrt Start of Service, Device wrt
        // ADF and Start of Service wrt ADF
        ArrayList<TangoCoordinateFramePair> framePairs = new ArrayList<TangoCoordinateFramePair>();
        framePairs.add(new TangoCoordinateFramePair(
                TangoPoseData.COORDINATE_FRAME_START_OF_SERVICE,
                TangoPoseData.COORDINATE_FRAME_DEVICE));
        framePairs.add(new TangoCoordinateFramePair(
                TangoPoseData.COORDINATE_FRAME_AREA_DESCRIPTION,
                TangoPoseData.COORDINATE_FRAME_DEVICE));
        framePairs.add(new TangoCoordinateFramePair(
                TangoPoseData.COORDINATE_FRAME_AREA_DESCRIPTION,
                TangoPoseData.COORDINATE_FRAME_START_OF_SERVICE));

        mTango.connectListener(framePairs, new Tango.OnTangoUpdateListener() {
            @Override
            public void onXyzIjAvailable(TangoXyzIjData xyzij) {
                // Not using XyzIj data for this sample
            }

            // Listen to Tango Events
            @Override
            public void onTangoEvent(final TangoEvent event) {
            }

            @Override
            public void onPoseAvailable(TangoPoseData pose) {

                boolean updateRenderer = false;
                // Make sure to have atomic access to Tango Data so that
                // UI loop doesn't interfere while Pose call back is updating
                // the data.
                synchronized (mSharedLock) {
                    // Check for Device wrt ADF pose, Device wrt Start of Service pose,
                    // Start of Service wrt ADF pose (This pose determines if the device
                    // is relocalized or not).
                    if (pose.baseFrame == TangoPoseData.COORDINATE_FRAME_AREA_DESCRIPTION
                            && pose.targetFrame == TangoPoseData.COORDINATE_FRAME_DEVICE) {

                        if (mIsRelocalized) {
                            updateRenderer = true;
                        }
                    } else if (pose.baseFrame == TangoPoseData.COORDINATE_FRAME_START_OF_SERVICE
                            && pose.targetFrame == TangoPoseData.COORDINATE_FRAME_DEVICE) {
                        if (!mIsRelocalized) {
                            updateRenderer = true;
                        }

                    } else if (pose.baseFrame == TangoPoseData.COORDINATE_FRAME_AREA_DESCRIPTION
                            && pose.targetFrame == TangoPoseData
                            .COORDINATE_FRAME_START_OF_SERVICE) {
                        if (pose.statusCode == TangoPoseData.POSE_VALID) {
                            mIsRelocalized = true;
                            // Set the color to green
                        } else {
                            mIsRelocalized = false;
                            // Set the color blue
                        }
                    }
                }

                final double deltaTime = (pose.timestamp - mPreviousPoseTimeStamp) *
                        SECS_TO_MILLISECS;
                mPreviousPoseTimeStamp = pose.timestamp;
                mTimeToNextUpdate -= deltaTime;

                if (mTimeToNextUpdate < 0.0) {
                    mTimeToNextUpdate = UPDATE_INTERVAL_MS;

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            synchronized (mSharedLock) {
//                                mSaveAdfButton.setEnabled(mIsRelocalized);
//                                mRelocalizationTextView.setText(mIsRelocalized ?
//                                        getString(R.string.localized) :
//                                        getString(R.string.not_localized));
                            }
                        }
                    });
                }
            }

            @Override
            public void onFrameAvailable(int cameraId) {
                // We are not using onFrameAvailable for this application.
            }
        });
    }
}
