package fr.musee.adr.adrmusee;

import android.content.Context;
import android.widget.ListView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;

import fr.musee.adr.adrmusee.adapter.ProductAdapter2;


/**
     * Created by Admin on 5/26/2017.
     */

    public class FirebaseClient  {

        Context c;
        String DB_URL;
        ListView listView;
        Firebase firebase;
        ArrayList<Product> productlist= new ArrayList<>();
        fr.musee.adr.adrmusee.adapter.ProductAdapter2 ProductAdapter2;



        public  FirebaseClient(Context c, String DB_URL, ListView listView)
        {
            this.c= c;
            this.DB_URL= DB_URL;
            this.listView= listView;


            Firebase.setAndroidContext(c);
            firebase=new Firebase(DB_URL);

        }

        public  void savedata(String name,float price, String url)
        {
            Product product= new Product();
            product.setName(name);
            product.setProductImage(url);
            product.setPrice(price);
            firebase.push().setValue(product);

        }

        public  void refreshdata()
        {
            firebase.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    getupdates(dataSnapshot);
                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {

                }
            });}

        public void getupdates(DataSnapshot dataSnapshot){

            productlist.clear();
            for(DataSnapshot ds : dataSnapshot.getChildren()){
                Product product= new Product();
                product.setName((String) ds.child("name").getValue());
                product.setProductImage((String) ds.child("image").getValue());
                product.setPrice((double) ds.child("price").getValue());
                productlist.add(product);

            }
            if(productlist.size()>0)
            {
                ProductAdapter2=new ProductAdapter2(c, productlist);
                listView.setAdapter(ProductAdapter2);
            }else
            {
                Toast.makeText(c, "Aucun produit actuellement", Toast.LENGTH_SHORT).show();
            }
        }

    }

