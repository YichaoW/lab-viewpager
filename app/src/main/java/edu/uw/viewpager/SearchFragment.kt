package edu.uw.viewpager

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText




/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [SearchFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [SearchFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class SearchFragment : Fragment() {

    private var listener: OnSearchListener? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView =  inflater.inflate(R.layout.fragment_search, container, false)
        val button = rootView.findViewById<Button>(R.id.btn_search)
        val text = rootView.findViewById<View>(R.id.txt_search) as EditText
        val searchTerm = text.text.toString()
        button.setOnClickListener{
            listener!!.onSearchSubmitted(searchTerm)
        }
        return rootView
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnSearchListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }


    internal interface OnSearchListener {
        fun onSearchSubmitted(searchTerm: String)
    }

    companion object {
        fun newInstance() =
            SearchFragment().apply {
                val args = Bundle()
                val fragment = SearchFragment()
                fragment.arguments = args
                return fragment
            }
    }
}
