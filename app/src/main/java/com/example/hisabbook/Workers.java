package com.example.hisabbook;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

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
import java.util.zip.Inflater;

public class Workers extends Fragment implements Adapter.OnClicker {

    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private final FirebaseFirestore fstore = FirebaseFirestore.getInstance();
    private final String firebaseid = mAuth.getCurrentUser().getUid();
    View view_store;
    private String transferrable[];




    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_workers, container, false);


        return root;
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view_store=view;
        final NavController navController = Navigation.findNavController(view);
        final RecyclerView worker= (RecyclerView) view.findViewById(R.id.worker_list);
        worker.setLayoutManager(new LinearLayoutManager(getActivity()));
        DocumentReference dr = fstore.collection("users").document(firebaseid);
        dr.collection("Workers").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    List<String> list = new ArrayList<>();
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        list.add(document.getId());
                    }
                     transferrable= list.toArray(new String[0]);

                    worker.setAdapter(new Adapter(Workers.this,transferrable));
                    }}});







        FloatingActionButton fab = view.findViewById(R.id.button_add_worker);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.action_nav_worker_to_button_add_worker);


            }
        });
    }


    @Override
    public void onItemClick(int position) {
        Toast.makeText(getActivity(), "position is "+position, Toast.LENGTH_SHORT).show();

       Intent tntodetail=new Intent(getActivity(),Worker_display_detail.class);
        tntodetail.putExtra("worker_name",transferrable[position]);
       startActivity(tntodetail);



    }
}


