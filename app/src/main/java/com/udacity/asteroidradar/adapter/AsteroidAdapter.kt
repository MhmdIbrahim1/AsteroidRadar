package com.udacity.asteroidradar.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

import com.udacity.asteroidradar.data.Asteroid
import com.udacity.asteroidradar.databinding.ItemAsteroidBinding


class AsteroidAdapter(private val onClickListener: OnClickListener) :
    ListAdapter<Asteroid, AsteroidAdapter.AsteroidPropertyViewHolder>(DiffCallback) {

    class AsteroidPropertyViewHolder(private var binding: ItemAsteroidBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(asteroid: Asteroid) {
            binding.data = asteroid

            binding.executePendingBindings()
        }

        companion object{
            /** this fun we're basically returning this class which created*/
            fun from(parent: ViewGroup): AsteroidPropertyViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemAsteroidBinding.inflate(layoutInflater,parent,false)
                return AsteroidPropertyViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AsteroidPropertyViewHolder {
        return AsteroidPropertyViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: AsteroidPropertyViewHolder, position: Int) {
        val asteroid = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(asteroid)
        }
        holder.bind(asteroid)
    }

    class OnClickListener(val clickListener: (asteroid: Asteroid) -> Unit) {
        fun onClick(asteroid: Asteroid) = clickListener(asteroid)
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Asteroid>() {

        override fun areItemsTheSame(oldItem: Asteroid, newItem: Asteroid): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Asteroid, newItem: Asteroid): Boolean {
            return oldItem.id == newItem.id
        }
    }

}
