package com.jasmeet.roomdb.adapters

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.jasmeet.roomdb.R
import com.jasmeet.roomdb.activities.MainActivity
import com.jasmeet.roomdb.databinding.ItemPostBinding
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

            val mFragmentManager = (mContext as MainActivity).supportFragmentManager
            val mFragmentTransaction = mFragmentManager.beginTransaction()
            val mPostDetailsFragment = PostDetailsFragment()

            val mBundle = Bundle()

            mBundle.putString("title",item.title)
            mBundle.putString("body",item.body)
            mBundle.putString("userId",item.userId.toString())
            mBundle.putString("id",item.id.toString())

            mPostDetailsFragment.arguments = mBundle
            mFragmentTransaction.replace(R.id.frameLayout,mPostDetailsFragment).addToBackStack(null).commit()
        }
    }

    override fun getItemCount(): Int {
        return mPosts.size
    }
}



