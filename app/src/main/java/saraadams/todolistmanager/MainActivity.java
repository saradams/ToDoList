package saraadams.todolistmanager;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {

    private ArrayList<String> todo;
    private ArrayAdapter<String> todoAdaptor;
    private ListView list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.todo = new ArrayList<String>();
        this.list = (ListView) findViewById(R.id.lstTodoItems);
        this.todoAdaptor = new TodoArrayAdapter(getApplicationContext(), R.layout.row, todo);
        list.setAdapter(todoAdaptor);
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
        if (id == R.id.menuItemAdd) {

            EditText newItem = (EditText)findViewById(R.id.edtNewItem);
            todo.add((newItem.getText()).toString());
            newItem.setText("");
            todoAdaptor.notifyDataSetChanged();
        }

        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                final Dialog deleteItem = new Dialog(MainActivity.this);
                LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

                View dialogView = inflater.inflate(R.layout.dialog, null);

                ((TextView)dialogView.findViewById(R.id.title)).setText(todo.get(position));
                Button deleteBtn = (Button) dialogView.findViewById(R.id.deleteBtn);

                deleteItem.setContentView(dialogView);


                deleteBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        todo.remove(position);
                        todoAdaptor.notifyDataSetChanged();
                        deleteItem.dismiss();
                    }
                });

                deleteItem.show();
                return false;
            }
        });


        return super.onOptionsItemSelected(item);
    }
}
