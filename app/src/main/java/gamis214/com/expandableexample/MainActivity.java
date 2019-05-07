package gamis214.com.expandableexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private HashMap<String, List<String>> lstMap;
    private List<String> lstString1,lstString2,lstString3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createListMap();
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new CustomAdapterMain(recyclerView,this,lstMap));
    }

    private void createListMap(){
        lstMap = new HashMap<>();
        lstString1 = new ArrayList<>();
        lstString2 = new ArrayList<>();
        lstString3 = new ArrayList<>();

        lstString1.add("a");
        lstString1.add("b");
        lstString1.add("c");
        lstString1.add("d");
        lstString1.add("e");

        lstMap.put("Letras",lstString1);

        lstString2.add("1");
        lstString2.add("2");
        lstString2.add("3");
        lstString2.add("4");
        lstString2.add("5");
        lstString2.add("6");
        lstString2.add("7");

        lstMap.put("Numeros",lstString2);

        lstString3.add("Manzana");
        lstString3.add("Pera");
        lstString3.add("Uvas");
        lstString3.add("Platano");

        lstMap.put("Frutas",lstString3);
    }
}
