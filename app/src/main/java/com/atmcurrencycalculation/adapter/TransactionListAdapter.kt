package com.atmcurrencycalculation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.atmcurrencycalculation.R
import com.atmcurrencycalculation.model.ATMCurrencyCalculationModel

class TransactionListAdapter() :
    RecyclerView.Adapter<TransactionListAdapter.ViewHolder>() {
    private val arrayList = ArrayList<ATMCurrencyCalculationModel>()
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.pattern_transaction_history, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val record = arrayList[position]

        holder.atmAmountValue.text = record.amount.toString()
        holder.atm2000CurrencyNoteCount.text = record.rs2000.toString()
        holder.atm500CurrencyNoteCount.text = record.rs500.toString()
        holder.atm200CurrencyNoteCount.text = record.rs200.toString()
        holder.atm100CurrencyNoteCount.text = record.rs100.toString()
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val atmAmountValue: AppCompatTextView = itemView.findViewById(R.id.atmAmountValueTextView)
        val atm100CurrencyNoteCount: AppCompatTextView =
            itemView.findViewById(R.id.atm100CurrencyNote)
        val atm200CurrencyNoteCount: AppCompatTextView =
            itemView.findViewById(R.id.atm200CurrencyNote)
        val atm500CurrencyNoteCount: AppCompatTextView =
            itemView.findViewById(R.id.atm500CurrencyNote)
        val atm2000CurrencyNoteCount: AppCompatTextView =
            itemView.findViewById(R.id.atm2000CurrencyNote)
    }

    fun setList(newList: ArrayList<ATMCurrencyCalculationModel>) {
        arrayList.clear()
        arrayList.addAll(newList)
        notifyDataSetChanged()
    }

    fun getLast(): ATMCurrencyCalculationModel? {
        return arrayList.lastOrNull()
    }
}