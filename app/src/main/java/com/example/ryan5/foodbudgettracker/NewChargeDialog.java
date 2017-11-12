package com.example.ryan5.foodbudgettracker;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Ryan5 on 11/11/2017.
 */

public class NewChargeDialog extends DialogFragment {

    private Double bill;
    private String name;
    EditText txtName;
    EditText txtBill;

    public Dialog onCreateDialog(Bundle savedInstanceBundle) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        View view = getActivity().getLayoutInflater().inflate(R.layout.charge_layout, null);

        Button btnDone = (Button) view.findViewById(R.id.btnDone);
        txtName = (EditText) view.findViewById(R.id.txtName);
        txtBill = (EditText) view.findViewById(R.id.txtBill);

        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = txtName.getText().toString();
                bill = Double.parseDouble(txtBill.getText().toString());

                Charges charge = new Charges(name, bill);

                try{
                    MainActivity callingActivity = (MainActivity) getActivity();
                    callingActivity.addCharge(charge);
                }catch(Exception e){

                }
            }
        });

        return builder.create();
    }
}
