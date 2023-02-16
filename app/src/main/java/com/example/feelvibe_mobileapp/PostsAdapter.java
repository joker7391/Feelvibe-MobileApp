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
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.ArrayList;
import java.util.List;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.MyViewHolder> {
    private Context context;
    private List<PostModel>postModelList;

    public PostsAdapter(Context context){
        this.context = context;
        postModelList = new ArrayList<>();
    }

    public void addPost(PostModel postModel){
        postModelList.add(postModel);
        notifyDataSetChanged();
    }

    public void clearPosts(){
        postModelList.clear();
        notifyDataSetChanged();
    }

    @androidx.annotation.NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@androidx.annotation.NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_view,parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@androidx.annotation.NonNull MyViewHolder holder, int position) {
     PostModel postModel = postModelList.get(position);
     if(postModel.getPostImage()!=null){
         holder.postImage.setVisibility(View.VISIBLE);
         Glide.with(context).load(postModel.getPostImage()).into(holder.postImage);
     }else{
         holder.postImage.setVisibility(View.GONE);
     }
     holder.postText.setText(postModel.getPostText());

     String uid = postModel.getPostId();
     FirebaseFirestore.getInstance().collection("Users").document(uid).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
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
        private TextView userName,postText;
        private ImageView userProfile,postImage,like,comment;

        public MyViewHolder(@NonNull View itemView){
            super(itemView);

            userName = itemView.findViewById(R.id.userName);
            postText = itemView.findViewById(R.id.postText);
            userProfile = itemView.findViewById(R.id.userProfile);
            like = itemView.findViewById(R.id.like);
            postImage =itemView.findViewById(R.id.postImage);
            comment= itemView.findViewById(R.id.comment);


        }
    }
}
