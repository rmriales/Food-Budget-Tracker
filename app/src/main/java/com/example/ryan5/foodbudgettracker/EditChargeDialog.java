package com.example.ryan5.foodbudgettracker;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Ryan5 on 11/13/2017.
 */

public class EditChargeDialog extends DialogFragment {
    public Dialog onCreateDialog(Bundle savedInstanceBundle) {
        Bundle mArgs = getArguments();
        Double price = mArgs.getDouble("price");
        String name = mArgs.getString("name");
        final int index = mArgs.getInt("index");

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        View view = getActivity().getLayoutInflater().inflate(R.layout.charge_layout, null);


        Button btnDone = (Button) view.findViewById(R.id.btnDone);
        final EditText txtName = (EditText) view.findViewById(R.id.txtName);
        final EditText txtBill = (EditText) view.findViewById(R.id.txtBill);

        builder.setView(view).setMessage("Edit charge:");

        txtName.setText(name);
        txtBill.setText(String.valueOf(price));

        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = txtName.getText().toString();
                Double bill = Double.parseDouble(txtBill.getText().toString());

                Charges charge = new Charges();
                charge.setAmount(bill);
                charge.setPlace(name);

                try{
                    MainActivity callingActivity = (MainActivity) getActivity();
                    callingActivity.updateCharge(charge,index);
                }catch(Exception e){

                }
                dismiss();
            }
        });

        return builder.create();
    }
}
