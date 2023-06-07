package sg.edu.rp.c346.id22037444.simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText etNewToDo;
    Button btnAdd, btnClearAll, btnDelete;
    ListView lvToDo;
    Spinner spnAddOrRemove;

    ArrayList<String> alToDo;
    ArrayAdapter<String> aaToDo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNewToDo = findViewById(R.id.editNewToDo);
        btnAdd = findViewById(R.id.buttonAddItem);
        btnClearAll = findViewById(R.id.buttonClearItem);
        btnDelete = findViewById(R.id.buttonDeleteItem);
        lvToDo = findViewById(R.id.listViewToDo);
        spnAddOrRemove = findViewById(R.id.spinner);

        alToDo = new ArrayList<>();

        aaToDo = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, alToDo);

        lvToDo.setAdapter(aaToDo);

        spnAddOrRemove.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch(position){
                    case 0:
                        etNewToDo.setHint("Type in new task");
                        btnDelete.setEnabled(false);
                        btnAdd.setEnabled(true);
                        break;
                    case 1:
                        etNewToDo.setHint("Type in the index of the task to be removed");
                        btnDelete.setEnabled(true);
                        btnAdd.setEnabled(false);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newtodo = etNewToDo.getText().toString();
                alToDo.add(newtodo);
                aaToDo.notifyDataSetChanged();
                etNewToDo.setText(null);
            }
        });

        btnClearAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alToDo.clear();
                aaToDo.notifyDataSetChanged();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < alToDo.size(); i++){
                    String deletetodo = etNewToDo.getText().toString();
                    if(alToDo.get(i).equals(deletetodo)){
                        alToDo.remove(i);
                        aaToDo.notifyDataSetChanged();
                        break;
                    }
                    else{
                        Toast.makeText(MainActivity.this, "No items matched", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}