package com.example.feelvibe_mobileapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.ArrayList;
import java.util.List;

public class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.MyViewHolder> {
    private Context context;
    private List<CommentModel>postModelList;

    public CommentsAdapter(Context context){
        this.context = context;
        postModelList = new ArrayList<>();
    }

    public void addPost(CommentModel postModel){
        postModelList.add(postModel);
        notifyDataSetChanged();
    }

    public void clearPosts(){
        postModelList.clear();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@androidx.annotation.NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.comment_view,parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@androidx.annotation.NonNull MyViewHolder holder, int position) {
     CommentModel commentModel = postModelList.get(position);
     holder.comment.setText(commentModel.getCommentId());

     String uid = commentModel.getUserId();
     FirebaseFirestore
             .getInstance()
             .collection("Users")
             .document(uid)
             .get()
             .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                UserModel userModel = documentSnapshot.toObject(UserModel.class);
                if(userModel.getUserProfile()!=null){
                    Glide.with(context).load(userModel.getUserProfile()).into(holder.userProfile);
                }
                holder.userName.setText(userModel.getUserName());
            }
        });
    }

    @Override
    public int getItemCount() {
        return postModelList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView userName,comment;
        private ImageView userProfile;

        public MyViewHolder(@NonNull View itemView){
            super(itemView);

            userName = itemView.findViewById(R.id.userName);
            comment = itemView.findViewById(R.id.comment);
            userProfile = itemView.findViewById(R.id.userProfile);


        }
    }
}
