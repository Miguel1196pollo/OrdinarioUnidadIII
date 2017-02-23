package mx.edu.utng.orders;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import mx.edu.utng.orders.adapters.PlatformAdapter;
import mx.edu.utng.orders.adapters.ProductAdapter;
import mx.edu.utng.orders.model.Platform;
import mx.edu.utng.orders.model.Product;
import mx.edu.utng.orders.sqlite.DBOperations;

/**
 * Created by Toshiba on 23/02/2017.
 */
public class PlatformActivity extends AppCompatActivity {
    private EditText etImgFilename;
    private EditText etName;
    private Button btSavePlatform;
    private DBOperations operations;
    private Platform platform = new Platform();

    private List<Platform> platforms = new ArrayList<>();

    private PlatformAdapter adapter;
    private RecyclerView rvPlatforms;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_platform);
        operations = DBOperations.getDBOperations(getApplicationContext());
        platform.setIdPlatform("");
        initComponents();
    }

    private void initComponents(){
        etImgFilename = (EditText)findViewById(R.id.et_img_filename);
        etName = (EditText)findViewById(R.id.et_name);
        rvPlatforms = (RecyclerView) findViewById(R.id.rv_platform_list);
        rvPlatforms.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rvPlatforms.setLayoutManager(layoutManager);
        getPlatforms();

        adapter = new PlatformAdapter(platforms);
        adapter.setListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch (v.getId()){
                    case R.id.bt_delete_platform:
                        operations.deletePlatform(
                                platforms.get(rvPlatforms.getChildPosition((View)v.getParent().getParent())).getIdPlatform());
                        getPlatforms();
                        adapter.notifyDataSetChanged();
                        break;

                    case R.id.bt_edit_platform:

                        Cursor c = operations.getPlatformById(platforms.get(rvPlatforms.getChildPosition((View)v.getParent().getParent()))
                                .getIdPlatform());

                        if(c!=null){
                            if(c.moveToFirst()){
                                platform = new Platform(c.getString(1),
                                        c.getString(2),c.getString(3));
                            }
                            etImgFilename.setText(platform.getImgFilename());
                            etName.setText(platform.getName());

                        }else{

                        }

                        break;
                    default:
                        break;

                }

            }
        });
        rvPlatforms.setAdapter(adapter);
        btSavePlatform = (Button) findViewById(R.id.bt_save_platform);

        btSavePlatform.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!platform.getIdPlatform().equals("")) {
                    platform.setImgFilename(etImgFilename.getText().toString());
                    platform.setName(etName.getText().toString());

                    operations.updatePlatform(platform);
                } else {
                    platform = new Platform("", etImgFilename.getText().toString(),
                            etName.getText().toString());
                    operations.insertPlatform(platform);
                }
                // Log.d("Products","Products");
                // DatabaseUtils.dumpCursor(operations.getProducts());
                clearData();
                getPlatforms();
                adapter.notifyDataSetChanged();
            }
        });

    }

    private void getPlatforms(){

        Cursor  c= operations.getPlatform();
        platforms.clear();
        if(c!=null){
            while (c.moveToNext()){
                platforms.add(new Platform(c.getString(1),
                        c.getString(2), c.getString(3)));

            }
        }
    }

    private void clearData(){
        etImgFilename.setText("");
        etName.setText("");
        platform= null;
        platform = new Platform();
        platform.setIdPlatform("");
    }

}







