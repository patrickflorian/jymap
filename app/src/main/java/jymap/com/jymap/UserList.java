package jymap.com.jymap;

import android.database.MatrixCursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class UserList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);
        // Définition des colonnes
// NB : SimpleCursorAdapter a besoin obligatoirement d'un ID nommé "_id"
        String[] columns = new String[] { "_id", "col1", "col2" };

// Définition des données du tableau
        MatrixCursor matrixCursor= new MatrixCursor(columns);
        startManagingCursor(matrixCursor);
        matrixCursor.addRow(new Object[] { 1,"col1:ligne1","col2:ligne1" });
        matrixCursor.addRow(new Object[] { 2,"col1:ligne2","col2:ligne2" });

// on prendra les données des colonnes 1 et 2...
        String[] from = new String[] {"col1", "col2"};

// ...pour les placer dans les TextView définis dans "row_item.xml"
        int[] to = new int[] { R.id.textViewCol1, R.id.textViewCol2};

// création de l'objet SimpleCursorAdapter...
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.row_item, matrixCursor, from, to, 0);

// ...qui va remplir l'objet ListView
        ListView lv = (ListView) findViewById(R.id.lv);
        lv.setAdapter(adapter);
    }
}
