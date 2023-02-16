package com.example.feelvibe_mobileapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.feelvibe_mobileapp.databinding.ActivityNewsFeedBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

public class NewsFeedActivity extends AppCompatActivity {
    ActivityNewsFeedBinding binding;
    private PostsAdapter postsAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNewsFeedBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        postsAdapter=new PostsAdapter(this);
        binding.postRecyclerView.setAdapter(postsAdapter);
        binding.postRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        loadPosts();

        binding.goCreatePost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(NewsFeedActivity.this, CreatePostActivity.class));
            }
        });
    }

    private void loadPosts(){
        FirebaseFirestore
                .getInstance()
                .collection("Posts").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                     postsAdapter.clearPosts();
                        List<DocumentSnapshot> dsList= queryDocumentSnapshots.getDocuments();
                     for (DocumentSnapshot ds:dsList){
                         PostModel postModel =ds.toObject(PostModel.class);
                         postsAdapter.addPost(postModel);
                     }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(NewsFeedActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}