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


import driver.UsbSerialDriver;
import driver.UsbSerialPort;
import driver.UsbSerialProber;

import java.io.IOException;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    //private static final String TAG = "TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button btn = (Button) findViewById(R.id.button);
        final Button btnE = (Button) findViewById(R.id.btnE);
        final Button btnA = (Button) findViewById(R.id.btnA);
        final Button btnD = (Button) findViewById(R.id.btnD);
        final Button btnS = (Button) findViewById(R.id.btnS);

        btn.setOnClickListener(new View.OnClickListener() {
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
//            int numBytesRead = port.read(buffer, 1000);
//            Log.d(TAG, "Read " + numBytesRead + " bytes.");
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

    public void clickS(){

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
//            int numBytesRead = port.read(buffer, 1000);
//            Log.d(TAG, "Read " + numBytesRead + " bytes.");
            port.close();
        } catch (IOException e) {
            // Deal with error.
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
}
