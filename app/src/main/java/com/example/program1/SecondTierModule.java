package com.example.program1;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SecondTierModule extends AppCompatActivity {
//
//    ItemAdapter itemAdapter;
//    Context thisContext;
    ListView myListView;
    String[] foods;
    String[] prices;
    String[] descriptions;
//    TextView progressTextView;
//    Map<String,Integer> foodMap = new LinkedHashMap<String, Integer>();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_layer_module);

        Resources res = getResources();
        myListView = (ListView) findViewById(R.id.myListView);
        foods = res.getStringArray(R.array.foods);
        prices = res.getStringArray(R.array.prices);
        descriptions = res.getStringArray(R.array.descriptions);

        ItemAdapter itemAdapter = new ItemAdapter(this, foods, prices, descriptions);
        myListView.setAdapter(itemAdapter);

        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent showImage = new Intent(getApplicationContext(), ImageItem.class);
                showImage.putExtra("com.example.program1.ITEM_INDEX",position);
                startActivity(showImage);
            }
        });

//        myListView.setAdapter(new ArrayAdapter<String>(this, R.layout.item_list, foods));

//        progressTextView = (TextView) findViewById(R.id.progressTextView);
//        thisContext = this;

//        progressTextView.setText("");

        Button backButton = (Button) findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent secondTierIntent = new Intent(getApplicationContext(), AppTierModule.class);
//                startActivity(secondTierIntent);
                onBackPressed();
            }
        });

//        Button getDataButton = (Button) findViewById(R.id.getDataButton);
//        getDataButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                GetData retreiveData = new GetData();
//                retreiveData.execute("");
//            }
//        });
//
//
//    }
//
//    private class GetData extends AsyncTask<String, String, String> {
//
//        String msg = "";
//
//        static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
//
//        static final String DB_URL = "jdbc:mysql://" +
//                DBConn.DATABASE_URL + "/" +
//                DBConn.DATABASE_NAME;
//
//        @Override
//        protected void onPreExecute() {
//            progressTextView.setText("Connecting to Database...");
//        }
//
//        @Override
//        protected String doInBackground(String... strings) {
//            Connection conn = null;
//            Statement statement = null;
//
//            try {
//                Class.forName(JDBC_DRIVER);
//                conn = DriverManager.getConnection(DB_URL, DBConn.DATABASE_USER, DBConn.DATABASE_PASSWORD);
//
//                statement = conn.createStatement();
//                String query = "select * from food";
//                ResultSet resultSet = statement.executeQuery(query);
//
//                while (resultSet.next()){
//                    String name = resultSet.getString("name_food");
//                    int price = resultSet.getInt("price_food");
//
//                    foodMap.put(name, price);
//                }
//
//                msg = "Process complete.";
//
//                resultSet.close();
//                statement.close();
//                conn.close();
//
//            } catch (SQLException connError){
//                msg = "An exception was thrown for JDBC";
//                connError.printStackTrace();
//            } catch (ClassNotFoundException e) {
//                msg = "A class not found exception";
//                e.printStackTrace();
//            } finally {
//                try {
//                    if (statement != null){
//                        statement.close();
//                    }
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//
//                try {
//                    if (conn != null){
//                        statement.close();
//                    }
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            }
//            return null;
//        }
//        @Override
//        protected void onPostExecute(String msg){
//            progressTextView.setText(this.msg);
//
//            if (foodMap.size()>0){
//                itemAdapter = new ItemAdapter(thisContext, foodMap);
//                myListView.setAdapter(itemAdapter);
//            }
//        }
    }
//
//    public void previousButton(){
//        TextView textView;
//        textView = findViewById(R.id.backButton);
//        Intent backIntent = new Intent(getApplicationContext(), AppTierModule.class);
//        startActivity(backIntent);
//    }
}
