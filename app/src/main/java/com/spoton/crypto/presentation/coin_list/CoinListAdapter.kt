package com.spoton.crypto.presentation.coin_list

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.spoton.crypto.R
import com.spoton.crypto.common.toFiveDecimalStringWithDollar
import com.spoton.crypto.common.toTwoDecimalStringWithPercentage
import com.spoton.crypto.databinding.CoinItemLayoutBinding
import com.spoton.crypto.domain.model.Coin

class CoinListAdapter(private var coinList: List<Coin>) :
    RecyclerView.Adapter<CoinListAdapter.CoinListViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): CoinListViewHolder {
        val binding =
            CoinItemLayoutBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return CoinListViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: CoinListViewHolder, position: Int) {
        setText(viewHolder, position)
    }

    override fun getItemCount(): Int {
        return coinList.size
    }

    private fun setText(viewHolder: CoinListViewHolder, position: Int) {
        val change = coinList[position].changePercent24Hr
        val price = coinList[position].priceUsd

        viewHolder.binding.rank.text = coinList[position].rank
        viewHolder.binding.name.text = coinList[position].name
        viewHolder.binding.change.text = change.toTwoDecimalStringWithPercentage()
        viewHolder.binding.price.text = price.toFiveDecimalStringWithDollar()

        if (change.toDouble() > 0) {
            setTextColor(viewHolder, R.color.green)
        } else {
            setTextColor(viewHolder, R.color.red)
        }
    }

    private fun setTextColor(viewHolder: CoinListViewHolder, colorId: Int) {
        viewHolder.binding.change.setTextColor(
            viewHolder.itemView.context.resources.getColor(
                colorId,
                null
            )
        )
        viewHolder.binding.price.setTextColor(
            viewHolder.itemView.context.resources.getColor(
                colorId,
                null
            )
        )
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(coinList: List<Coin>) {
        this.coinList = coinList
        notifyDataSetChanged()
    }

    inner class CoinListViewHolder(val binding: CoinItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)

}