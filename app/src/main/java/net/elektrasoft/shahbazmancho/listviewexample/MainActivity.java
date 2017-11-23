package net.elektrasoft.shahbazmancho.listviewexample;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    EditText searchETbx;

    CustomAdapter customAdapter;
    ArrayList<DataModel> mydataModel=new ArrayList<DataModel>();
    ArrayList<DataModel> searchResults= new ArrayList<DataModel>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView=(ListView)findViewById(R.id.listViewmyrides);
        searchETbx=(EditText)findViewById(R.id.searchTbx);

        //add some data in myList
        mydataModel.add(new DataModel("Shahbaz","Mancho"));
        mydataModel.add(new DataModel("Fahad","Minhas"));
        mydataModel.add(new DataModel("Irfan","Latif"));
        mydataModel.add(new DataModel("Mughees","Ahmad"));

        searchResults.add(new DataModel("Shahbaz","Mancho"));
        searchResults.add(new DataModel("Fahad","Minhas"));
        searchResults.add(new DataModel("Irfan","Latif"));
        searchResults.add(new DataModel("Mughees","Ahmad"));


        customAdapter= new CustomAdapter(searchResults,getApplicationContext());

        listView.setAdapter(customAdapter);//sets the adapter for listView

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                DataModel dataModel= mydataModel.get(position);

                Snackbar.make(view, dataModel.getFname()+"  "+dataModel.getLname(), Snackbar.LENGTH_LONG)
                        .setAction("No action", null).show();
            }
        });

        searchETbx.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count)
            {
                String searchString = searchETbx.getText().toString();
                int textLength = searchString.length();

                searchResults.clear();
                for (int i = 0; i < mydataModel.size(); i++)
                {
                    String Fname = mydataModel.get(i).fname;

                    if (textLength <= Fname.length()) {
                        //compare the String in EditText with Names in the ArrayList
                        if (searchString.equalsIgnoreCase(Fname.substring(0, textLength)))
                        {
                            searchResults.add(mydataModel.get(i));
                        }
                    }
                }

                customAdapter.notifyDataSetChanged();

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
}
