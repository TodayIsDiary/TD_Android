package dsm.todayisdiary.android.td_android.diary

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import dsm.todayisdiary.android.td_android.data.RetrofitBuilder
import dsm.todayisdiary.android.td_android.data.response.diary.Diary
import dsm.todayisdiary.android.td_android.data.response.diary.DiaryCategoryResponse
import dsm.todayisdiary.android.td_android.data.util.RecyclerViewAdapter
import dsm.todayisdiary.android.td_android.databinding.FragmentHomeAngryBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeAngryFragment : Fragment() {
    private var _binding: FragmentHomeAngryBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeAngryBinding.inflate(inflater, container, false)

        loadData()

        return binding.root
    }

    private fun setAdapter(diaryList: List<Diary>) {
        val adapter = RecyclerViewAdapter()
        adapter.postList = diaryList.toMutableList()
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(activity)
    }

    private fun loadData() {
        val retrofitBuilder = RetrofitBuilder

        retrofitBuilder.diaryApi.diaryCategoryResponse(
            "ANGRY"
        ).enqueue(object : Callback<DiaryCategoryResponse> {
            override fun onFailure(call: Call<DiaryCategoryResponse>, t: Throwable) {
                Log.d("통신 실패", "${t.message}")
            }

            override fun onResponse(
                call: Call<DiaryCategoryResponse>,
                response: Response<DiaryCategoryResponse>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        setAdapter(it.diarys)
                    }
                } else {
                    Log.d("실패", response.message())
                }
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}