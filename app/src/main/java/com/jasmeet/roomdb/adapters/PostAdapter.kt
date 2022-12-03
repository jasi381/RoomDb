package com.jasmeet.roomdb.adapters

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.jasmeet.roomdb.R
import com.jasmeet.roomdb.activities.MainActivity
import com.jasmeet.roomdb.databinding.ItemPostBinding
import com.jasmeet.roomdb.fragments.AllDetailsFragment
import com.jasmeet.roomdb.fragments.AllDetailsFragmentDirections
import com.jasmeet.roomdb.fragments.PostDetailsFragment
import com.jasmeet.roomdb.models.PostsItem

class PostAdapter(private val mContext: Context, private val mPosts : MutableList<PostsItem>)
    :RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    inner class PostViewHolder(view: View):ViewHolder(view){
        val binding =ItemPostBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        return PostViewHolder(LayoutInflater
            .from(mContext)
            .inflate(R.layout.item_post,parent,false))
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {

        val item = mPosts[position]

        holder.binding.Userid.text = item.id.toString()
        holder.binding.Title.text = item.title


        holder.itemView.setOnClickListener {

            //pass the data to the fragment using safe args
            val action = AllDetailsFragmentDirections.actionAllDetailsFragmentToPostDetailsFragment(item)
            val navController: NavController = Navigation.findNavController(it)
            navController.navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return mPosts.size
    }
}



