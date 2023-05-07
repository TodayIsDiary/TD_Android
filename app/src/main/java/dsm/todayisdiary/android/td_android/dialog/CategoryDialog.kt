package dsm.todayisdiary.android.td_android.dialog

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import dsm.todayisdiary.android.td_android.databinding.CategoryDialogBinding

class CategoryDialog : DialogFragment() {
    private var _binding: CategoryDialogBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = CategoryDialogBinding.inflate(layoutInflater)
        val view = binding.root
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        binding.happy.setOnClickListener {
            categoryClickListener.onHappyClicked()
            dismiss()
        }
        binding.sad.setOnClickListener {
            categoryClickListener.onSadClicked()
            dismiss()
        }
        binding.angry.setOnClickListener {
            categoryClickListener.onAngryClicked()
            dismiss()
        }
        binding.surprise.setOnClickListener {
            categoryClickListener.onSurpriseClicked()
            dismiss()
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    interface OnCategoryClickListener {
        fun onHappyClicked()
        fun onSadClicked()
        fun onAngryClicked()
        fun onSurpriseClicked()
    }

    fun setCategoryClickListener(categoryClickListener: OnCategoryClickListener) {
        this.categoryClickListener = categoryClickListener
    }

    private lateinit var categoryClickListener: OnCategoryClickListener
}