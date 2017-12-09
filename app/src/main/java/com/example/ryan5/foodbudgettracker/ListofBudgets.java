package com.example.ryan5.foodbudgettracker;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;

/**
 * Created by Ryan5 on 12/8/2017.
 */

public class ListofBudgets extends Activity {
    private Double balance;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);

        Button btnReset = (Button) findViewById(R.id.btnReset);
        Button btnEditBalance = (Button) findViewById(R.id.editBalance);
        ListView listBudgets = (ListView) findViewById(R.id.chargeList);



    }

    public class BudgetsAdapter extends BaseAdapter{
        priavte ArrayList<Budgets> = new ArrayList<Budgets>();
        @Override
        public int getCount() {
            return 0;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            return null;
        }
    }


}
