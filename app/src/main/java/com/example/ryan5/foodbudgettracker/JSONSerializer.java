package com.example.ryan5.foodbudgettracker;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ryan5 on 10/15/2017.
 * Used to save the Account Objects to Phone Memory
 */

public class JSONSerializer {
    private String fileName;
    private Context context;

    public JSONSerializer(String fn, Context c){
        fileName = fn;
        context = c;
    }



    public void save(List<Charges> acc) throws IOException, JSONException{
        //Make an array of JSON Objects
        JSONArray jsArray = new JSONArray();

        //Load it with Accounts
        for(Charges a : acc){
            jsArray.put(a.createJSONObject());
        }

        //Write it to the apps private disk space
        Writer writer = null;

        try{
            //get and open filer location
            OutputStream out = context.openFileOutput(fileName, context.MODE_PRIVATE);
            //create output stream to that file location
            writer = new OutputStreamWriter(out);
            //start the writer
            writer.write(jsArray.toString());
        } finally{
            if(writer != null){
                //delete writer
                writer.close();
            }
        }
    }

    public ArrayList<Charges> load() throws IOException,JSONException{
        ArrayList<Charges> accList = new ArrayList<Charges>();
        BufferedReader reader = null;

        try{
            InputStream in = context.openFileInput(fileName);
            reader = new BufferedReader(new InputStreamReader(in));
            StringBuilder jsonString = new StringBuilder();
            String line;

            while((line = reader.readLine()) != null){
                jsonString.append(line);
            }

            JSONArray jsArray = (JSONArray) new JSONTokener(jsonString.toString()).nextValue();
            for(int i = 0; i < jsArray.length(); i++){
                accList.add(new Charges(jsArray.getJSONObject(i)));
            }
        }catch(FileNotFoundException e){
            //ignore since happens on fresh start
        }finally {
            if (reader != null) {
                reader.close();
            }
        }
        return accList;
    }
}