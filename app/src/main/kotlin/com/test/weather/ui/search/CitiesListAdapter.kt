package com.test.weather.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.test.weather.R
import com.test.weather.data.db.entity.CityEntity
import com.test.weather.databinding.ItemSearchBinding

class CitiesListAdapter(itemSelect: ((CityEntity) -> Unit)) :RecyclerView.Adapter<CitiesListAdapter.ViewHolder>(){
    lateinit var citiesList:List<CityEntity>

    private var onItemClick: ((CityEntity) -> Unit)?=itemSelect

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemSearchBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_search, parent, false)
        return CitiesListAdapter.ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return if(::citiesList.isInitialized) citiesList.size else 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(citiesList[position])
        holder.itemView.setOnClickListener {onItemClick?.invoke(citiesList[position])};
    }

    class ViewHolder(private val binding:ItemSearchBinding ):RecyclerView.ViewHolder(binding.root) {
        private val viewModel = CityViewModel()
        fun bind(geoName: CityEntity) {
            viewModel.bind(geoName)
            binding.viewModel = viewModel
        }
    }
}
