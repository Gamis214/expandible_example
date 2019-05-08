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
    private HashMap<String, List<String>> lstMap = new HashMap<>();

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
        for(int i=0;i<=2;i++){
            List<String> lst = new ArrayList<>();
            switch (i){
                case 0:
                    lst.add("a");
                    lst.add("b");
                    lst.add("c");
                    lst.add("d");
                    lst.add("e");
                    lstMap.put("Letras",lst);
                    break;
                case 1:
                    lst.add("1");
                    lst.add("2");
                    lst.add("3");
                    lst.add("4");
                    lst.add("5");
                    lst.add("6");
                    lst.add("7");
                    lstMap.put("Numeros",lst);
                    break;
                case 2:
                    lst.add("Manzana");
                    lst.add("Pera");
                    lst.add("Uvas");
                    lst.add("Platano");
                    lstMap.put("Frutas",lst);
                    break;
            }

        }
    }
}
