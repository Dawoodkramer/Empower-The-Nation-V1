package com.varsitycollege.upskill2.ui.contactus

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.varsitycollege.upskill2.databinding.FragmentContactusBinding

class ContactUsFragment : Fragment() {

    private var _binding: FragmentContactusBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val contactUsViewModel =
            ViewModelProvider(this).get(ContactUsViewModel::class.java)

        _binding = FragmentContactusBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textContact
        contactUsViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}