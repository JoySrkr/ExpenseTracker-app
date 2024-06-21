package com.example.expancetracker.views.activites;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.expancetracker.adapters.TransactionsAdapter;
import com.example.expancetracker.databinding.FragmentTransactionsBinding;
import com.example.expancetracker.models.Transaction;
import com.example.expancetracker.utils.Constants;
import com.example.expancetracker.utils.Helper;
import com.example.expancetracker.viewmodel.MainViewModel;
import com.example.expancetracker.views.fragments.AddTransactionFragment;
import com.example.expancetracker.R;
import com.example.expancetracker.databinding.ActivityMainBinding;
import com.example.expancetracker.views.fragments.StatsFragment;
import com.example.expancetracker.views.fragments.TransactionsFragment;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.tabs.TabLayout;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import io.realm.Realm;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {

     ActivityMainBinding binding;
     Calendar calendar;
     /*
      0 = Daily
      1= Monthly
      2 = Calender
      3=Summary
      4 = Notes
     *
     * */


     //Realm realm;

    public MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        EdgeToEdge.enable(this);
        setContentView(binding.getRoot());

        viewModel = new ViewModelProvider(this).get(MainViewModel.class);

        //setupDatabase();

        setSupportActionBar(binding.toolBar);
        getSupportActionBar().setTitle("Transaction");

        Constants.setCategories();

        calendar = Calendar.getInstance();

        FragmentTransaction transaction= getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content,new TransactionsFragment());
        transaction.commit();

        binding.bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentTransaction transaction= getSupportFragmentManager().beginTransaction();
                if(item.getItemId() == R.id.transactions ){
                    getSupportFragmentManager().popBackStack();
                } else if (item.getItemId() == R.id.stats) {
                    transaction.replace(R.id.content,new StatsFragment());
                    transaction.addToBackStack(null);
                }
                transaction.commit();
                return true;
            }
        });



       /* TransactionsAdapter transactionsAdapter = new TransactionsAdapter(this, transactions);
        binding.transactionsList.setLayoutManager(new LinearLayoutManager(this));
        binding.transactionsList.setAdapter(transactionsAdapter); */
    }

    public void getTransactions() {
        viewModel.getTransactions(calendar);
    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.top_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }
}