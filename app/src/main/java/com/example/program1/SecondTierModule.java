package com.example.program1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SecondTierModule extends AppCompatActivity {
//
//    ItemAdapter itemAdapter;
//    Context thisContext;
//    ListView myListView;
//    String[] foods;
//    String[] prices;
//    String[] descriptions;

  DatabaseReference databaseReference;
  RecyclerView myRecyclerView;
  RecyclerView.Adapter myRecyclerViewAdapter;
  RecyclerView.LayoutManager myRecyclerViewLayoutMgr;
  ArrayList<Food> foodArrayList;
//    TextView progressTextView;
//    Map<String,Integer> foodMap = new LinkedHashMap<String, Integer>();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_layer_module);

        myRecyclerView = (RecyclerView) findViewById(R.id.foodRecyclerView);
        myRecyclerView.setHasFixedSize(true);
        myRecyclerViewLayoutMgr = new LinearLayoutManager(this);
        myRecyclerView.setLayoutManager(myRecyclerViewLayoutMgr);

      databaseReference = FirebaseDatabase.getInstance().getReference();
      databaseReference.child("foods").addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
          foodArrayList = new ArrayList<>();
          System.out.println(dataSnapshot.getChildren());
          for (DataSnapshot dataSnapshotIter : dataSnapshot.getChildren())
          {
            System.out.println(dataSnapshotIter.getValue());
            Food food = (Food) dataSnapshotIter.getValue(Food.class);
            food.setKey(dataSnapshotIter.getKey());
            foodArrayList.add(food);
          }
          myRecyclerViewAdapter = new ItemAdapter(foodArrayList, SecondTierModule.this);
          myRecyclerView.setAdapter(myRecyclerViewAdapter);
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {
          System.out.println(databaseError.getDetails()+" "+databaseError.getMessage());
        }
      });


//        Resources res = getResources();
//        myListView = (ListView) findViewById(R.id.myListView);
//        foods = res.getStringArray(R.array.foods);
//        prices = res.getStringArray(R.array.prices);
//        descriptions = res.getStringArray(R.array.descriptions);




//      ChildEventListener childEventListener = new ChildEventListener() {
//        @Override
//        public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
////          Log.d(TAG, "onChildAdded:" + dataSnapshot.getKey());
//
//          Food food = dataSnapshot.getValue(Food.class);
//          System.out.println(food.getId());
//
//
//        }
//
//        @Override
//        public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//
//        }
//
//        @Override
//        public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
//
//        }
//
//        @Override
//        public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//
//        }
//
//        @Override
//        public void onCancelled(@NonNull DatabaseError databaseError) {
//
//        }
//      };
//      databaseReference.addChildEventListener(childEventListener);

//        ItemAdapter itemAdapter = new ItemAdapter(this, foods, prices, descriptions);
//        myListView.setAdapter(itemAdapter);

//        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Intent showImage = new Intent(getApplicationContext(), ImageItem.class);
//                showImage.putExtra("com.example.program1.ITEM_INDEX",position);
//                startActivity(showImage);
//            }
//        });

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

    public static Intent getActiveIntent(Activity activity)
    {
      return new Intent(activity, SecondTierModule.class);
    }
//
//    public void previousButton(){
//        TextView textView;
//        textView = findViewById(R.id.backButton);
//        Intent backIntent = new Intent(getApplicationContext(), AppTierModule.class);
//        startActivity(backIntent);
//    }
}
