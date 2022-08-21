package com.example.proyectosegundobimestre

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.security.AccessControlContext

class RecyclerView_Transactions(
    private val context: GUI_TransactionsHistorial,
    private val transactionsList: List<FirestoreTransaction>,
    private val recyclerView:RecyclerView
) : RecyclerView.Adapter<RecyclerView_Transactions.MyViewHolder> () {

    inner class MyViewHolder(view:View):RecyclerView.ViewHolder(view){
        val tv_cv_day: TextView
        val tv_cv_month: TextView
        val tv_cv_concept: TextView
        val tv_cv_account_source: TextView
        val tv_cv_account_destiny: TextView
        val tv_cv_amount_add_sub: TextView
        val tv_cv_amount_after_transaction: TextView

        init {
            tv_cv_day = view.findViewById(R.id.card_view_day)
            tv_cv_month = view.findViewById(R.id.card_view_month)
            tv_cv_concept = view.findViewById(R.id.card_view_concept)
            tv_cv_account_source = view.findViewById(R.id.card_view_source_account)
            tv_cv_account_destiny = view.findViewById(R.id.card_view_destiny_account)
            tv_cv_amount_add_sub = view.findViewById(R.id.card_view_add_sub)
            tv_cv_amount_after_transaction = view.findViewById(R.id.card_view_new_balance)
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView_Transactions.MyViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(
                R.layout.card_view_transaction_historial,
                parent,
                false
            )
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RecyclerView_Transactions.MyViewHolder, position: Int) {
        val transactionRV = transactionsList[position]
        holder.tv_cv_day.text = transactionRV.transaction_day
        holder.tv_cv_month.text = transactionRV.transaction_month
        holder.tv_cv_concept.text = transactionRV.transaction_concept
        holder.tv_cv_account_source.text = "${transactionRV.user_source_name} - ${transactionRV.num_account_source}"
        holder.tv_cv_account_destiny.text = "${transactionRV.user_destiny_name} - ${transactionRV.num_account_destiny}"

        if(transactionRV.transaction_sign.equals("-")){
            holder.tv_cv_amount_add_sub.setTextColor(Color.parseColor("#C83636"))
            holder.tv_cv_amount_add_sub.text = "${transactionRV.transaction_sign} $${String.format("%.2f",transactionRV.add_sub_value)}"
        }else{
            holder.tv_cv_amount_add_sub.text = "${transactionRV.transaction_sign} $${String.format("%.2f",transactionRV.add_sub_value)}"
        }

        holder.tv_cv_amount_after_transaction.text = "$${String.format("%.2f",transactionRV.transaction_balance_after)}"

    }

    override fun getItemCount(): Int {
        return transactionsList.size
    }

}