package com.example.hisabbook;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hisabbook.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class Category extends Fragment implements Adapter.OnClicker {
    String transferrable[];



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.category, container, false);


        return root;
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final NavController navController = Navigation.findNavController(view);
        super.onViewCreated(view, savedInstanceState);
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        final FirebaseFirestore fstore = FirebaseFirestore.getInstance();
        final String firebaseid = mAuth.getCurrentUser().getUid();

        final RecyclerView category= (RecyclerView) view.findViewById(R.id.category_list);
        category.setLayoutManager(new LinearLayoutManager(getActivity()));
        DocumentReference dr = fstore.collection("users").document(firebaseid);
        dr.collection("Categories").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    List<String> list = new ArrayList<>();
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        list.add(document.getId());
                    }
                    String transferrable[]= list.toArray(new String[0]);
                    category.setAdapter(new Adapter(Category.this,transferrable));
                }}});







        FloatingActionButton fab = view.findViewById(R.id.button_add_category);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.action_nav_category_to_button_add_categ);

            }
            });
        }

    @Override
    public void onItemClick(int position) {

    }
}



