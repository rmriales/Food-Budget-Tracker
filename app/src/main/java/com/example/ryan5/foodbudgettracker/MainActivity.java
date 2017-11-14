package com.example.ryan5.foodbudgettracker;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Button btnReset;
    private ListView listOfCharges;
    private TextView txtbalance;
    private Double balance = 120.00;
    private ChargesAdapter mChargesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setImageResource(R.drawable.ic_action_name);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NewChargeDialog dialog = new NewChargeDialog();
                dialog.show(getFragmentManager(), null);
            }
        });


        btnReset = (Button) findViewById(R.id.btnReset);
        listOfCharges = (ListView) findViewById(R.id.chargeList);

        mChargesAdapter = new ChargesAdapter();
        listOfCharges.setAdapter(mChargesAdapter);

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mChargesAdapter.delete();
                txtbalance = (TextView) view.findViewById(R.id.txtAmount);
                txtbalance.setText("$120.00");
            }
        });

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

    @Override
    protected void onPause(){
        super.onPause();
        mChargesAdapter.saveCharges();
    }


    public void addCharge(Charges c){
        mChargesAdapter.addCharge(c);
    }


    public class ChargesAdapter extends BaseAdapter {

        ArrayList<Charges> charges = new ArrayList<Charges>();
        ImageButton btnEdit;
        ImageButton btnDel;
        JSONSerializer serializer;
        private double balance;

        public ChargesAdapter(){
            serializer = new JSONSerializer("Charges.JSON",MainActivity.this.getApplicationContext());

            try{
                charges = serializer.load();
            }catch(Exception e){
                charges = new ArrayList<Charges>();
                Log.e("Error Load accounts: ", "", e);
            }
        }

        public void saveCharges(){
            try {
                serializer.save(charges);
            }catch(Exception e){
                Log.e("eEror Save accounts", "", e);
            }
        }

        @Override
        public Charges getItem(int item){return charges.get(item);}

        @Override
        public long getItemId(int item){return item;}

        @Override
        public int getCount(){return charges.size();}

        @Override
        public View getView(int item, View view, ViewGroup group){

            if(view == null){
                LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.list_view_layout, group, false);
            }

            TextView txtPlace = (TextView) view.findViewById(R.id.txtPlace);
            TextView txtAmount = (TextView) view.findViewById(R.id.txtAmount);
            final TextView txtBalance = (TextView) findViewById(R.id.remBalance);
            btnEdit = (ImageButton) view.findViewById(R.id.btnEdit);
            btnDel = (ImageButton) view.findViewById(R.id.btnDel);

            final Charges tempCharge = charges.get(item);
            
            if(item == 0){
                Double balance = 120.00;
                balance =  balance - tempCharge.getAmount();
            }else{
                Double balance = Double.parseDouble(txtBalance.getText().toString().substring(1));
                balance =  balance - tempCharge.getAmount();  
            }
            
            Resources res = getResources();
            String text = String.format(res.getString(R.string.updated_balance), balance);
            txtBalance.setText(text);

            txtPlace.setText(tempCharge.getPlace());
            txtAmount.setText(tempCharge.getAmount().toString());

            btnEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bundle mArgs = new Bundle();
                    mArgs.putDouble("price",tempCharge.getAmount());
                    mArgs.putString("name",tempCharge.getPlace());

                    EditChargeDialog dialog = new EditChargeDialog();
                    dialog.setArguments(mArgs);
                    dialog.show(getFragmentManager(), "123");
                    delete(charges.indexOf(tempCharge));
                }
            });

            btnDel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    delete(charges.indexOf(tempCharge));
                    if(charges.size()<1){
                        txtBalance.setText("$120.00");
                    }
                }
            });

            return view;
        }

        public void addCharge(Charges c){
            charges.add(c);
            notifyDataSetChanged();
        }

        public void delete(){
            charges.clear();
            notifyDataSetChanged();
        }

        public void delete(int item){
            charges.remove(item);
            notifyDataSetChanged();
        }

    }


}
