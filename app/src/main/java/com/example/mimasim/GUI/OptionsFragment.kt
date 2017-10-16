package com.example.mimasim.GUI

import android.app.Fragment
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import com.example.mimasim.R

/**
 * Created by root on 13.10.17.
 */
class OptionsFragment : Fragment(){

    var optionsCallback : OptionsCallback? = null

    interface OptionsCallback{
        fun saveOptions(optionState: OptionsState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.options, container , false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        view?.findViewById<Button>(R.id.optionsSaveButton)?.setOnClickListener{
            //TODO read all the options stuff and pu into the Optionstate
            val currentOptionState = OptionsState()

            currentOptionState.fillZeroes = view?.findViewById<CheckBox>(R.id.FillZerosCheck).isChecked
            currentOptionState.invertViews = view?.findViewById<CheckBox>(R.id.invertViewsCheck).isChecked

            optionsCallback?.saveOptions(currentOptionState)
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        try {
            optionsCallback = context as OptionsCallback
        } catch (e : ClassCastException){
            throw ClassCastException(activity.toString() + " must implement MimaFragmentCallback")
        }
    }
}