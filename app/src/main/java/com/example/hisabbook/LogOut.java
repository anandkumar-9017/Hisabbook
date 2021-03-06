package com.example.hisabbook;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;


public class LogOut extends Fragment {

    FirebaseAuth mAuth;
    FirebaseAuth.AuthStateListener mAuthstateListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_log_out, container, false);



    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mAuth.getInstance()
                .signOut();
        Toast.makeText(getActivity(), "You are successfully logged out.", Toast.LENGTH_SHORT).show();


        Intent back_to_reg=new Intent(getActivity(),MainActivity.class);
        startActivity(back_to_reg);
}}
