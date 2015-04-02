package com.example.macs;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class MyActivity extends ActionBarActivity implements AdapterView.OnItemClickListener{


    Context c;
    int[] images={R.drawable.img1,R.drawable.img2,R.drawable.img3,R.drawable.img4,R.drawable.img5,R.drawable.img6};
    String[] values={"Item1","Item2","Item3","Item4","Item5","Item6"};
    ListView l1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);//activity_main.xml is provided in the folder
        c=this;
        l1= (ListView) findViewById(R.id.listView);
        //ArrayAdapter<String> adapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,values);//Simplest adapter
        //ArrayAdapter<String> adapter=new ArrayAdapter(c,R.layout.row_test,R.id.textView,values);//Adapter with custom layout
        CustomAdapter adapter=new CustomAdapter(c,values,images);//Custom adapter with custom layout
        l1.setAdapter(adapter);
        l1.setOnItemClickListener(this);
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        TextView temp= (TextView) view.findViewById(R.id.textView);
        Toast.makeText(c, temp.getText().toString()+" position="+i, Toast.LENGTH_SHORT).show();
    }
}

class CustomAdapter extends ArrayAdapter<String>
{
    Context con;
    String[] itemvalues;
    int[] images;
    CustomAdapter(Context c,String[] values,int[] img)
    {
        super(c,R.layout.row_test,R.id.textView,values);
        this.con=c;
        this.itemvalues=values;
        this.images=img;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        LayoutInflater inflater= (LayoutInflater) con.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view= inflater.inflate(R.layout.row_test,parent,false);
        TextView text1= (TextView) view.findViewById(R.id.textView);
        ImageView img1= (ImageView) view.findViewById(R.id.imageView);
        text1.setText(itemvalues[position]);
        img1.setImageResource(images[position]);
        return view;
    }
}