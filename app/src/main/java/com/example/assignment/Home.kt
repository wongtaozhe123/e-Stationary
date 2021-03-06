package com.example.assignment

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment.Interface.ItemSClickListener
import com.example.assignment.ViewHolder.ItemSViewHolder
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.android.synthetic.main.home.*

import android.os.PersistableBundle
import android.widget.Toast
import com.google.firebase.firestore.DocumentReference
import kotlinx.android.synthetic.main.wishlist.*
import kotlinx.android.synthetic.main.home.*
import kotlinx.android.synthetic.main.home.imgbtnAccount
import kotlinx.android.synthetic.main.home.imgbtnCart
import kotlinx.android.synthetic.main.home.imgbtnCustService
import kotlinx.android.synthetic.main.home.imgbtnHome
import kotlinx.android.synthetic.main.register.*

class Home:AppCompatActivity() {

    internal lateinit var adapter: FirestoreRecyclerAdapter<ItemS, ItemSViewHolder>

    /*private var adapter: ProductFirestoreRecyclerAdapter? = null*/

    internal var items: MutableList<ItemS> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.assignment.R.layout.home)

        imgbtnHome.setOnClickListener{
            startActivity(Intent(this, Home::class.java))
        }

        imgbtnCustService.setOnClickListener{
            startActivity(Intent(this, CustomerService::class.java))
        }

        imgbtnCart.setOnClickListener{
            startActivity(Intent(this, AddToCart::class.java))
        }

        imgbtnAccount.setOnClickListener(){
            startActivity(Intent(this, Wishlist::class.java))
        }

        button3.setOnClickListener{
            startActivity(Intent(this, ItemDetails::class.java))
        }

        recycleData.setHasFixedSize(true)
        recycleData.layoutManager = LinearLayoutManager(this)

        retrieveData()
        setData()

        //var db: DocumentReference = FirebaseFirestore.getInstance().document(documentPath)
        val rootRef = FirebaseFirestore.getInstance()
        val query = rootRef!!.collection("writingInstrument").orderBy(
            "itemName",
            Query.Direction.ASCENDING
        )



   /*     val rootRef = FirebaseFirestore.getInstance()
        val query = rootRef!!.collection("writingInstrument").orderBy(
            "productName",
            Query.Direction.ASCENDING
        )

        val options = FirestoreRecyclerOptions.Builder<ItemS>().setQuery(query, ItemS::class.java).build()
*/
    }

    private fun retrieveData() {
        items.clear()
        val db = FirebaseDatabase.getInstance()
            .reference
            .child("001")
        db.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (itemSnapShot in snapshot.children) {
                    val item = itemSnapShot.getValue(ItemS::class.java)
                    items.add(item!!)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.d("Error", " " + error.message)
            }

        })
    }

    private fun setData() {
        val query = FirebaseFirestore.getInstance().collection("writingInstrument")
        //val query = FirebaseDatabase.getInstance().reference.child("writingInstrument")
        val options = FirestoreRecyclerOptions.Builder<ItemS>()
            .setQuery(query, ItemS::class.java)
            .build()


        adapter = object:FirestoreRecyclerAdapter<ItemS, ItemSViewHolder>(options) {

/*            override fun getItemViewType(position: Int): Int {

                return
            }*/

            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemSViewHolder {

                val itemView = LayoutInflater
                    .from(parent.context)
                    .inflate(R.layout.list_layout, parent, false)

                return ItemSViewHolder(itemView)
            }

            override fun onBindViewHolder(p0: ItemSViewHolder, p1: Int, p2: ItemS) {

                p0.setItemSClickListener(p0.itemS_ClickListener)
            }

        }

    }

    /*class ProductViewHolder internal constructor(private val view: View) : RecyclerView.ViewHolder(
        view
    ) {
        internal fun setProductName(productName: String) {
            val textView = view.findViewById<TextView>(R.id.text_view)
            textView.text = productName
        }
    }*/

    /*private inner class ProductFirestoreRecyclerAdapter internal constructor(options: FirestoreRecyclerOptions<ItemS>) : FirestoreRecyclerAdapter<ItemS, ProductViewHolder>(
        options
    ) {
        override fun onBindViewHolder(
            productViewHolder: ProductViewHolder,
            position: Int,
            productModel: ItemS
        ) {
            productViewHolder.setProductName(productModel.itemName)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(
                R.layout.,
                parent,
                false
            )
            return ProductViewHolder(view)
        }
    }*/

    //listen for changes/updates
    override fun onStart() {
        super.onStart()
        adapter!!.startListening()
    }

    override fun onStop() {
        super.onStop()

        if (adapter != null) {
            adapter!!.stopListening()
        }
    }
}
