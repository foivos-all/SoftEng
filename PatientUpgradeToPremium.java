package com.example.healthcare;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.braintreepayments.cardform.view.CardForm;

public class PatientUpgradeToPremium extends AppCompatActivity {
    CardForm cardForm;
    Button submit;
    AlertDialog.Builder alertBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_upgrade_to_premium);

        cardForm = findViewById(R.id.card_form);
        submit = findViewById(R.id.btnBuy);

        cardForm.cardRequired(true)
                .expirationRequired(true)
                .cvvRequired(true)
                .postalCodeRequired(true)
                .mobileNumberRequired(true)
                .mobileNumberExplanation("Απαιτείται SMS για αυτό το νούμερο")
                .setup(PatientUpgradeToPremium.this);

        cardForm.getCvvEditText().setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_PASSWORD);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cardForm.isValid()) {
                    alertBuilder = new AlertDialog.Builder(PatientUpgradeToPremium.this);
                    alertBuilder.setTitle("Επιβεβαίωση πριν την αγορά");
                    alertBuilder.setMessage("Card number: " + cardForm.getCardNumber() + "\n" +
                            "Card expiry date: " + cardForm.getExpirationDateEditText().getText().toString() + "\n" +
                            "Card CVV: " + cardForm.getCvv() + "\n" +
                            "Postal code: " + cardForm.getPostalCode() + "\n" +
                            "Phone number: " + cardForm.getMobileNumber());
                    alertBuilder.setPositiveButton("ΕΠΙΒΕΒΑΙΩΣΗ", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            String type = "upgradeToPremium";
                            ConnectionHelper connectionHelper = new ConnectionHelper(PatientUpgradeToPremium.this);
                            connectionHelper.execute(type, Login.user_id);

                            dialogInterface.dismiss();
                            Toast.makeText(PatientUpgradeToPremium.this, "Ευχαριστούμε για την αγορά", Toast.LENGTH_LONG).show();
                        }
                    });
                    alertBuilder.setNegativeButton("ΑΚΥΡΩΣΗ", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });
                    AlertDialog alertDialog = alertBuilder.create();
                    alertDialog.show();
                }else {
                    Toast.makeText(PatientUpgradeToPremium.this, "Συμπληρώστε την φόρμα πρώτα!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}