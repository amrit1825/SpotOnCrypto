package com.spoton.crypto.presentation.coin_list

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.spoton.crypto.R
import com.spoton.crypto.databinding.FragmentCoinlistBinding

// Fragment to show coins list.
class CoinListFragment : Fragment() {

    private val coinListViewModel: CoinListViewModel by activityViewModels()
    private lateinit var binding: FragmentCoinlistBinding
    private val handler = Handler(Looper.getMainLooper())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCoinlistBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUp()
        setListener()
        setObserver()
    }

    // Runnable will be executed when fragment is visible and interactive.
    override fun onResume() {
        super.onResume()
        handler.post(task)
    }

    // Removing runnable if user is about to leave the fragment.
    override fun onPause() {
        super.onPause()
        handler.removeCallbacks(task)
    }

    // Runnable for fetching updated data every 10 seconds.
    private val task = object : Runnable {
        override fun run() {
            coinListViewModel.getCoins()
            handler.postDelayed(this, 10000)
        }
    }

    private fun setUp() {
        binding.coinList.layoutManager = LinearLayoutManager(context)
        binding.coinList.adapter = CoinListAdapter(emptyList())
    }

    // Swipe refresh layout for refreshing data on screen.
    private fun setListener() {
        val swipeRefresh = binding.swipeRefresh
        swipeRefresh.setOnRefreshListener {
            swipeRefresh.isRefreshing = false
            coinListViewModel.getCoins()
        }
    }

    // Setting observer to observe data changes.
    private fun setObserver() {
        coinListViewModel.state.observe(viewLifecycleOwner) { data -> updateUI(data) }
    }

    private fun updateUI(coinListState: CoinListState) {
        when {
            coinListState.isLoading -> {
                showMessage(resources.getString(R.string.loading))
            }
            coinListState.error.isNotEmpty() -> {
                showMessage(coinListState.error)
            }
            else -> {
                hideMessage(true)
                val adapter = binding.coinList.adapter as CoinListAdapter
                adapter.updateData(coinListState.coins)
            }
        }
    }

    private fun showMessage(message: String) {
        hideMessage(false)
        binding.message.text = message
    }

    // Function to handle visibility of coin list, loading & error messages.
    private fun hideMessage(hide: Boolean) {
        if (hide) {
            binding.coinList.visibility = View.VISIBLE
            binding.message.visibility = View.GONE
        } else {
            binding.coinList.visibility = View.GONE
            binding.message.visibility = View.VISIBLE
        }
    }

}