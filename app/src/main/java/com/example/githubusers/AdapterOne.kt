package com.example.githubusers

import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.ImageRequest
import com.example.githubusers.databinding.ItemRvBinding
import com.squareup.picasso.RequestCreator
lateinit var requestQueue: RequestQueue
class AdapterOne(
    private val arrayList: ArrayList<User>
) :
    RecyclerView.Adapter<AdapterOne.VH>() {

    inner class VH(private var itemRV: ItemRvBinding) : RecyclerView.ViewHolder(itemRV.root) {
        fun onBind(user: User, position: Int) {
            itemRV.name.text = user.name
            val imageRequest = ImageRequest(
                user.img,
                object : Response.Listener<Bitmap> {
                    override fun onResponse(response: Bitmap?) {
                        itemRV.imgUser.setImageBitmap(response)
                    }
                },
                0,
                0,
                ImageView.ScaleType.CENTER_CROP,
                Bitmap.Config.ARGB_8888,
                object : Response.ErrorListener {
                    override fun onErrorResponse(error: VolleyError?) {

                    }
                })
            requestQueue.add(imageRequest)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(ItemRvBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.onBind(arrayList[position], position)

    }

    override fun getItemCount(): Int = arrayList.size

    interface RVClickGroups {
    }
}