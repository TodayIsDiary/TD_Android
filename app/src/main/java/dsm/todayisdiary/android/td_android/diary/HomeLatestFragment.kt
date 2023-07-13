package dsm.todayisdiary.android.td_android.diary

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import dsm.todayisdiary.android.td_android.data.RetrofitBuilder
import dsm.todayisdiary.android.td_android.data.response.diary.Diary
import dsm.todayisdiary.android.td_android.data.response.diary.DiaryDetailResponse
import dsm.todayisdiary.android.td_android.data.response.diary.DiaryLatestResponse
import dsm.todayisdiary.android.td_android.data.util.RecyclerViewAdapter
import dsm.todayisdiary.android.td_android.databinding.FragmentHomeLatestBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeLatestFragment : Fragment() {
    private var _binding: FragmentHomeLatestBinding? = null
    private val binding get() = _binding!!
    private val adapter = RecyclerViewAdapter()
    val retrofitBuilder = RetrofitBuilder

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeLatestBinding.inflate(inflater, container, false)

        loadData()
        detailDiary()
        refresh()

        return binding.root
    }

    fun refresh() {
        binding.refresh.setOnRefreshListener {
            loadData()
            binding.refresh.isRefreshing = false
        }
    }

    private fun detailDiary() {
        adapter.itemClickListener = object : RecyclerViewAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                val item = adapter.postList[position].boardId
                retrofitBuilder.diaryApi.diaryDetailResponse(item)
                    .enqueue(object : Callback<DiaryDetailResponse> {
                        override fun onFailure(call: Call<DiaryDetailResponse>, t: Throwable) {
                            Log.d("통신 실패", "${t.message}")
                        }

                        override fun onResponse(
                            call: Call<DiaryDetailResponse>,
                            response: Response<DiaryDetailResponse>
                        ) {
                            if (response.isSuccessful) {
                                val intent = Intent(activity, PostActivity::class.java)
                                intent.putExtra("boardId", response.body()!!.boardId)
                                intent.putExtra("title", response.body()!!.title)
                                intent.putExtra("content", response.body()!!.content)
                                intent.putExtra("category", response.body()!!.category)
                                intent.putExtra("boardTime", response.body()!!.boardTime)
                                intent.putExtra("writer", response.body()!!.writer)
                                intent.putExtra("heart", response.body()!!.heart)
                                intent.putExtra("commentCount", response.body()!!.commentCount)
                                intent.putExtra("userId", response.body()!!.userId)
                                intent.putExtra("imageUrl", response.body()?.imageUrl)
                                intent.putExtra("writerProfile", response.body()!!.writerProfile)
                                intent.putExtra("liked", response.body()!!.liked)
                                startActivity(intent)
                            } else {
                                Log.d("실패", response.message())
                            }
                        }
                    })
            }
        }
    }

    private fun setAdapter(diaryList: List<Diary>) {
        adapter.postList = diaryList.toMutableList()
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(activity)
    }

    private fun loadData() {
        retrofitBuilder.diaryApi.diaryLatestList()
            .enqueue(object : Callback<DiaryLatestResponse> {
                override fun onFailure(call: Call<DiaryLatestResponse>, t: Throwable) {
                    Log.d("통신 실패", "${t.message}")
                }

                override fun onResponse(
                    call: Call<DiaryLatestResponse>,
                    response: Response<DiaryLatestResponse>
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