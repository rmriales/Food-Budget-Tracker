package com.example.ryan5.foodbudgettracker;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.support.design.internal.SnackbarContentLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Ryan5 on 12/3/2017.
 */

public class ChangeBudgetAmountDialog extends DialogFragment {
    public Dialog onCreateDialog(Bundle SavedInstanceState){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        View view = getActivity().getLayoutInflater().inflate(R.layout.change_balance, null);

        Button done = view.findViewById(R.id.btnNewBudget);
        final EditText newBudget = view.findViewById(R.id.txtNewBudget);

        builder.setView(view).setMessage("Enter New Budget Amount");

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Double newBalance = Double.parseDouble(newBudget.getText().toString());

                try{
                    MainActivity callingActivity = (MainActivity) getActivity();
                    callingActivity.updateBudgetAmount(newBalance);
                }catch(Exception e){
                    Toast.makeText(getActivity(),"Trouble updating Budget Amount.",Toast.LENGTH_LONG).show();
                }
                dismiss();
            }
        });

        return builder.create();
    }
}
