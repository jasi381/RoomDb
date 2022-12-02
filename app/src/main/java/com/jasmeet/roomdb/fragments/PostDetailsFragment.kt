package com.jasmeet.roomdb.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.jasmeet.roomdb.R
import com.jasmeet.roomdb.databinding.FragmentPostDetailsBinding
import com.jasmeet.roomdb.models.PostsItem


class PostDetailsFragment : Fragment() {

    private lateinit var _binding: FragmentPostDetailsBinding
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPostDetailsBinding.inflate(layoutInflater)


        val bundle = arguments
        val id = bundle?.getString("id")
        val userId = bundle?.getString("userId")
        val title = bundle?.getString("title")
        val body = bundle?.getString("body")

        binding.tvBody.text = body
        binding.tvId.text = id
        binding.tvTitle.text = title
        binding.tvUserId.text = userId

        val data :PostsItem = PostsItem(id = id!!.toInt(),userId = userId!!.toInt(),title = title,body = body)

        addToWatchlist(data)

        return binding.root

    }
    var watchList : ArrayList<String> ?= null
    var watchListIsChecked = false


    private fun addToWatchlist(data: PostsItem) {
        readData()

        watchListIsChecked = if (watchList!!.contains(data.id.toString())){
            binding.fav.setImageResource(R.drawable.star)
            true
        }else{
            binding.fav.setImageResource(R.drawable.star_outline)
            false
        }
        binding.fav.setOnClickListener {
            watchListIsChecked =
                if (!watchListIsChecked){
                    if(watchList!!.contains(data.id.toString())){
                        watchList!!.add(data.id.toString())
                    }
                    storeData()
                    binding.fav.setImageResource(R.drawable.star)
                    true
                }else{
                    binding.fav.setImageResource(R.drawable.star_outline)
                    watchList!!.remove(data.id.toString())
                    storeData()
                    false
                }
        }
    }

    private fun storeData(){
        val sharedPreferences = requireContext().getSharedPreferences("watchlist",Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val gson = Gson()
        val json = gson.toJson(watchList)
        editor.putString("watchlist", json)
        editor.apply()

    }

    private fun readData() {
        val sharedPreferences = requireContext().getSharedPreferences("watchlist",Context.MODE_PRIVATE)
        val gson = Gson()
        val json = sharedPreferences.getString("watchlist",ArrayList<String>().toString())
        val type = object : TypeToken<ArrayList<String>>() {}.type
        watchList = gson.fromJson(json,type)
    }


}

