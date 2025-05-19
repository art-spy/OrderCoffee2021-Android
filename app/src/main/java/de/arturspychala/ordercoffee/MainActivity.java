/**
 * IMPORTANT: Add your package below. Package name can be found in the project's AndroidManifest.xml file.
 * This is the package name our example uses:
 *
 * */

package de.arturspychala.ordercoffee;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothHeadset;
import android.bluetooth.BluetoothProfile;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
// import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

import androidx.appcompat.app.AppCompatActivity;

import static android.R.id.message;


public class MainActivity extends AppCompatActivity {

    String personName = "";
    int quantity = 0;

    boolean hasWhippedCream = false;
    boolean hasChocolate = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        displayMessage();
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        personName = getPersonName();
        if (personName.equals("")) {
            Toast.makeText(this, "Bitte Namen eingeben!", Toast.LENGTH_SHORT).show();
            return;
        }
        else if( coffeeOrderedToast() != null ) {
            return;
        }
        else {
            composeEmail("Kaffee Bestellung für " + personName, createOrderSummary());
        }



        /*String calculatedPrice = calculatePrice();

        String hasWhippedCreamGerman = "Nein";
        String hasChocolateGerman = "Nein";

        if (hasWhippedCream) {
            hasWhippedCreamGerman = "Ja";
        }

        if (hasChocolate) {
            hasChocolateGerman = "Ja";
        }

        displayMessage( createOrderSummary() );*/
    }

    private void composeEmail(String subject, String text) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, text);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }


    private String calculatePrice() {
        int pricePerCup = 5;

        if (hasWhippedCream){
            pricePerCup = pricePerCup + 1;
        }

        if (hasChocolate) {
            pricePerCup = pricePerCup + 2;
        }

        int priceInteger = quantity * pricePerCup;

        String priceCurrency = "" + NumberFormat.getCurrencyInstance().format(priceInteger);
        return priceCurrency;
    }


    public String createOrderSummary() {
        personName = getPersonName();

        String calculatedPrice = calculatePrice();

        String hasWhippedCreamGerman = "Nein";
        String hasChocolateGerman = "Nein";

        if (hasWhippedCream) {
            hasWhippedCreamGerman = "Ja";
        }

        if (hasChocolate) {
            hasChocolateGerman = "Ja";
        }

        return
                "Name: " + personName
                + "\nAnzahl: " + quantity + " Kaffee"
                + "\nSchlagsahne: " + hasWhippedCreamGerman
                + "\nSchokolade: " + hasChocolateGerman
                + "\nSumme: " + calculatedPrice
                + "\nVielen Dank :-)";
    }


    private String getPersonName() {
        EditText nameField = (EditText) findViewById(R.id.name_field);
        return personName = nameField.getText().toString();
    }


    public void decrement(View view) {
        if (quantity <= 0) {
            display(quantity);
            displayMessage();
        }
        else {
            quantity = quantity - 1;
            display(quantity);
            displayMessage();
        }

        coffeeOrderedToast();
    }

    public void increment(View view) {
        quantity = quantity + 1;
        display(quantity);
        displayMessage();
        System.out.println();
    }


    private String coffeeOrderedToast() {
        if (quantity == 0) {
            Toast.makeText(this, "Bitte mindestens einen Kaffee bestellen ;-)", Toast.LENGTH_SHORT).show();
            return "";
        }
        return null;
    }


    public void selectToppings (View view) {
        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whippedCreamCheckBox);
        hasWhippedCream = whippedCreamCheckBox.isChecked();

        CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.chocolateCheckBox);
        hasChocolate = chocolateCheckBox.isChecked();

        displayMessage();
    }


    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_textview);
        quantityTextView.setText("" + number);
    }


    /**
     * This method displays the given text on the screen.
     */
    public void displayMessage() {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_textview);
        orderSummaryTextView.setText(createOrderSummary());
    }

    public void displayMessageView(View view) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_textview);
        orderSummaryTextView.setText(createOrderSummary());
    }


    // private void showSubtotal () {
    // Alt
    // displayMessage("Zwischensumme: " + calculatePrice() );
    // }


/*    BluetoothHeadset mBluetoothHeadset;

    // Get the default adapter
    BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

    private BluetoothProfile.ServiceListener mProfileListener = new BluetoothProfile.ServiceListener()
    {
        public void onServiceConnected(int profile, BluetoothProfile proxy) {
            if (profile == BluetoothProfile.HEADSET) {
                mBluetoothHeadset = (BluetoothHeadset) proxy;
            }
        }
        public void onServiceDisconnected(int profile) {
            if (profile == BluetoothProfile.HEADSET) {
                mBluetoothHeadset = null;
            }
        }
    };

// Establish connection to the proxy.
mBluetoothAdapter.getProfileProxy(context, mProfileListener, BluetoothProfile.HEADSET);

// ... call functions on mBluetoothHeadset

// Close proxy connection after use.
mBluetoothAdapter.closeProfileProxy(mBluetoothHeadset);


    private TestKlasse testKlassenVariable = new TestKlasse() {
        int i;
        public void initialize(){
            i = 10;
        }
    }*/


}


/**
 * Originaler Quellcode

package de.arturspychala.ordercoffee;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import de.arturspychala.ordercoffee.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
 */
