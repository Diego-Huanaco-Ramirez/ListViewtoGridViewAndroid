package com.example.diego.candyworld.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.example.diego.candyworld.adapter.MyAdapter;
import com.example.diego.candyworld.R;

import java.util.ArrayList;
import java.util.List;

public class GridActivity extends AppCompatActivity {
    private List<String> nombres;
    private List<String> origen;
    private List<String> bandera;
    private GridView gridView;
    private MyAdapter myAdapter;

    int contador;
    int contar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid);
        bandera = new ArrayList<String>();
        bandera.add("diego");
        bandera.add("juan");
        bandera.add("jorge");
        bandera.add("matias");


        gridView = (GridView) findViewById(R.id.gridView);
        //nombres = new ArrayList<String>();

        nombres = getIntent().getStringArrayListExtra("nombres");


        //origen = new ArrayList<String>();
        origen = getIntent().getStringArrayListExtra("origen");
        contador = (getIntent().getExtras().getInt("contador"));
        contar = (getIntent().getExtras().getInt("contar"));


        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(GridActivity.this, "clic en " + nombres.get(i), Toast.LENGTH_LONG).show();
            }
        });
        //Enlazamos con nuestro adaptador personalizado
        myAdapter = new MyAdapter(this, R.layout.grid_item, nombres, origen);
        gridView.setAdapter(myAdapter);
        registerForContextMenu(gridView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_bar_menu, menu);
        MenuItem itemToHide = menu.findItem(R.id.switch_gridview);
        itemToHide.setVisible(false);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.add_item:
                //agregamos nuevo nombre

                this.nombres.add("agregar nombre " + (++contador));
                this.origen.add("gridview" + (contador));

                contar++;
                //Notificamos al adaptador del cambio producido
                this.myAdapter.notifyDataSetChanged();
                return true;
            case R.id.delete_item:
                //Toast.makeText(GridActivity.this, "eliminar", Toast.LENGTH_LONG).show();
                if (contar >= 0) {
                    this.origen.remove(contar);
                    this.nombres.remove(contar--);
                    if (contador > 0)
                        contador--;
                }
                this.myAdapter.notifyDataSetChanged();
                return true;

            case R.id.switch_listview:
                Intent intent = new Intent(GridActivity.this, ListActivity.class);
                intent.putStringArrayListExtra("nombres", (ArrayList<String>) nombres);
                intent.putStringArrayListExtra("bandera", (ArrayList<String>) bandera);
                intent.putStringArrayListExtra("origen", (ArrayList<String>) origen);
                intent.putExtra("contar", contar);
                intent.putExtra("contador", contador);
                startActivity(intent);
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        menu.setHeaderTitle(this.nombres.get(info.position));
        inflater.inflate(R.menu.context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {
            case R.id.delete_item:
                this.nombres.remove(info.position);
                this.myAdapter.notifyDataSetChanged();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }
}

