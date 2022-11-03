package com.udacity.asteroidradar.bindingadapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso
import com.udacity.asteroidradar.R
import com.udacity.asteroidradar.adapter.AsteroidAdapter
import com.udacity.asteroidradar.data.Asteroid

class BindingAdapters {
    companion object{
        @BindingAdapter("goneIfNotNull")
        @JvmStatic
        fun goneIfNotNull(view: View, it: Any?) {
            view.visibility = if (it != null) View.GONE else View.VISIBLE
        }

        @BindingAdapter("listData")
        @JvmStatic
        fun bindRecyclerView(recyclerView: RecyclerView, data: List<Asteroid>?) {
            if (!data.isNullOrEmpty()){
                val adapter = recyclerView.adapter as AsteroidAdapter
                adapter.submitList(data)
            }
        }

        @BindingAdapter("statusIcon")
        @JvmStatic
        fun bindAsteroidStatusImage(imageView: ImageView, isHazardous: Boolean) {

            if (isHazardous) {
                imageView.setImageResource(R.drawable.ic_status_potentially_hazardous)

            } else {

                imageView.setImageResource(R.drawable.ic_status_normal)
            }
        }

        @BindingAdapter("asteroidStatusImage")
        @JvmStatic
        fun bindDetailsStatusImage(imageView: ImageView, isHazardous: Boolean) {
            if (isHazardous) {
                imageView.contentDescription = imageView.context.getString(
                    R.string.potentially_hazardous_asteroid_image
                )
                imageView.setImageResource(R.drawable.asteroid_hazardous)
            } else {
                imageView.contentDescription = imageView.context.getString(
                    R.string.not_hazardous_asteroid_image
                )
                imageView.setImageResource(R.drawable.asteroid_safe)
            }
        }


        @BindingAdapter("astronomicalUnitText")
        @JvmStatic
        fun bindTextViewToAstronomicalUnit(textView: TextView, number: Double) {
            val context = textView.context
            textView.text = String.format(context.getString(R.string.astronomical_unit_format), number)
        }

        @BindingAdapter("kmUnitText")
        @JvmStatic
        fun bindTextViewToKmUnit(textView: TextView, number: Double) {
            val context = textView.context
            textView.text = String.format(context.getString(R.string.km_unit_format), number)
        }

        @BindingAdapter("velocityText")
        @JvmStatic
        fun bindTextViewToDisplayVelocity(textView: TextView, number: Double) {
            val context = textView.context
            textView.text = String.format(context.getString(R.string.km_s_unit_format), number)
        }

        @BindingAdapter("imageUrl")
        @JvmStatic
        fun bindImage(imageView: ImageView, imageUrl: String?) {
            imageView.load(imageUrl){
            crossfade(600)
            error(R.drawable.ic_no_photo)

            }
        }

    }
}