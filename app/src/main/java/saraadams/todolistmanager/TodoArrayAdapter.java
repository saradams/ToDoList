package saraadams.todolistmanager;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by sara on 3/17/2015.
 */
public class TodoArrayAdapter extends ArrayAdapter<String> {

    public TodoArrayAdapter(Context context, int resource, ArrayList<String> list) {
        super(context, resource, list);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View rowView = convertView;


        // first shown now
        if (rowView == null) {
            LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(R.layout.row, null);
        }

        TextView item = (TextView)rowView.findViewById(R.id.itemToAdd);

        //change coloring according to position
        if (position%2 == 0) {
            item.setTextColor(Color.RED);
        } else {
            item.setTextColor(Color.BLUE);
        }

        return super.getView(position, rowView, parent);

    }
}
