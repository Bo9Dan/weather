package com.test.weather.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.test.weather.R
import com.test.weather.data.db.entity.CityEntity
import com.test.weather.databinding.ItemCityBinding
import com.test.weather.ui.search.CityViewModel
import kotlinx.android.synthetic.main.item_city.view.*

class SavedPlacesAdapter(
        selectItem: ((CityEntity) -> Unit),
        deleteItem: (CityEntity) -> Unit
) : RecyclerView.Adapter<SavedPlacesAdapter.ViewHolder>() {

    lateinit var citiesList: List<CityEntity>

    private val onSelectItem: ((CityEntity) -> Unit)? = selectItem
    private val onDeleteItem: ((CityEntity) -> Unit)? = deleteItem

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SavedPlacesAdapter.ViewHolder {
        val binding: ItemCityBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context),
                R.layout.item_city, parent, false)
        return SavedPlacesAdapter.ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return if (::citiesList.isInitialized) citiesList.size else 0
    }

    override fun onBindViewHolder(holder: SavedPlacesAdapter.ViewHolder, position: Int) {
        holder.bind(citiesList[position])
        holder.itemView.setOnClickListener { onSelectItem?.invoke(citiesList[position]) }
        holder.itemView.buttonDelete.setOnClickListener { onDeleteItem!!.invoke(citiesList[position]) }
    }

    class ViewHolder(private val binding: ItemCityBinding) : RecyclerView.ViewHolder(binding.root) {
        private val viewModel = CityViewModel()

        fun bind(model: CityEntity) {
            viewModel.bind(model)
            binding.viewModel = viewModel
        }
    }

}
