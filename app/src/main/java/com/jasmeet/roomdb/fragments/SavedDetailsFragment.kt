package com.jasmeet.roomdb.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.jasmeet.roomdb.adapters.PostAdapter
import com.jasmeet.roomdb.api.RetrofitService
import com.jasmeet.roomdb.databinding.FragmentSavedDetailsBinding
import com.jasmeet.roomdb.models.PostsItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SavedDetailsFragment : Fragment() {

    private lateinit var _binding: FragmentSavedDetailsBinding
    private val binding get() = _binding
    private lateinit var watchList: ArrayList<String>
    private lateinit var watchListItem:  ArrayList<PostsItem>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSavedDetailsBinding.inflate(layoutInflater)
        readData()

            val resp = RetrofitService.retrofitInstance.getPost()

            resp.enqueue(object : Callback<List<PostsItem>?> {
                override fun onResponse(
                    call: Call<List<PostsItem>?>,
                    response: Response<List<PostsItem>?>
                ) {
                    val res = response.body()
                    if (res !=null){
                        watchListItem = ArrayList()
                            watchList.clear()

                        for (watchData in watchList){
                            for (item in res){
                                if (item.id.toString() == watchData){
                                    watchListItem.add(item)
                                }
                            }
                        }

                        binding.progressBar2.visibility = View.GONE
                        binding.recyclerView.adapter = PostAdapter(requireContext(),watchListItem)

                    }
                }

                override fun onFailure(call: Call<List<PostsItem>?>, t: Throwable) {
                    Toast.makeText(requireContext(),t.message.toString(),Toast.LENGTH_SHORT).show()
                }
            })



        return binding.root
    }

    private fun readData() {
        val sharedPreferences = requireContext().getSharedPreferences("watchlist", Context.MODE_PRIVATE)
        val gson = Gson()
        val json = sharedPreferences.getString("watchlist",ArrayList<String>().toString())
        val type = object : TypeToken<ArrayList<String>>() {}.type
        watchList = gson.fromJson(json,type)
    }

}